package com.app.kaidee.arch.mvi.lite

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.app.kaidee.arch.mvi.MviIntent
import com.app.kaidee.arch.mvi.MviViewState
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
abstract class MviLitePresenter<I : MviIntent, S : MviViewState>(
	initialState: S,
	schedulerProvider: SchedulerProvider
) : ViewModel() {

	private var currentState = initialState

	private val intentSubject = PublishSubject.create<I>()

	private val stateSubject = BehaviorSubject.createDefault(initialState)

	private val disposable = CompositeDisposable()

	init {
		disposable.add(
			intentSubject.observeOn(schedulerProvider.io())
				.publish(::process)
				.distinctUntilChanged()
				.observeOn(schedulerProvider.ui())
				.subscribe(stateSubject::onNext)
		)
	}

	fun dispatch(intent: I) {
		log("Dispatch : ${intent::class.java.simpleName}")
		intentSubject.onNext(intent)
	}

	fun states(): Observable<S> {
		return stateSubject.doOnNext { state ->
			log(state.toLogString())
		}
	}

	protected fun setState(reducer: S.() -> S): S {
		currentState = reducer(currentState)
		return currentState
	}

	override fun onCleared() {
		disposable.clear()
	}

	private fun process(intents: Observable<I>): Observable<S> {
		return Observable.merge(
			processorHolder(intents)
		)
	}

	abstract fun processorHolder(intents: Observable<I>): List<Observable<S>>

	protected inline fun <reified R> Observable<*>.filterIsInstance(): Observable<R> = ofType(R::class.java)

	protected open fun log(message: String) = println(message)

}