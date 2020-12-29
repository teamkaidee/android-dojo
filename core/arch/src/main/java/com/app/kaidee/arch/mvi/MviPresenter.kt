package com.app.kaidee.arch.mvi

import androidx.lifecycle.ViewModel
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * Object that will subscribes to a [MviView]'s [MviIntent]s,
 * process it and emit a [MviViewState] back.
 *
 * @param I Top class of the [MviIntent] that the [MviPresenter] will be subscribing
 * to.
 * @param S Top class of the [MviViewState] the [MviPresenter] will be emitting.
 */
open class MviPresenter<I : MviIntent, S : MviViewState, A : MviAction, R : MviResult>(
    private val initialState: S,
    private val schedulerProvider: SchedulerProvider,
    private val processorHolder: MviProcessorHolder<A, R>,
    private val reducerHolder: MviReducerHolder<R, S>,
    private val actionMapper: MviActionMapper<I, S, A>
) : ViewModel() {

    private val disposable by lazy {
        CompositeDisposable()
    }

    private val intentSubject = PublishSubject.create<I>()

    protected val resultObservable = shared()

    private val stateObservable = compose()

    private var currentState = initialState

    open fun dispatch(intent: I) {
        intentSubject.onNext(intent)
    }

    fun states(): Observable<S> {
        return stateObservable
    }

    fun currentState(): S {
        return currentState
    }

    protected fun addDisposable(d: Disposable) {
        disposable.add(d)
    }

    private fun shared(): Observable<R> {
        return intentSubject
            .observeOn(schedulerProvider.io())
            .map { intent ->
                actionMapper.mapToAction(intent, currentState)
            }
            .doOnNext { action ->
                log(action::class.java.simpleName)
            }
            .compose(processorHolder)
            .doOnNext { result ->
                log(result::class.java.simpleName)
            }
            .share()
    }

    private fun compose(): Observable<S> {
        return resultObservable.scan(initialState, reducerHolder)
            .distinctUntilChanged()
            .doOnNext { state ->
                currentState = state
                log(state.toLogString())
            }
            .replay(1)
            .autoConnect(0)
            .observeOn(schedulerProvider.ui())
    }

    protected open fun log(message: String) {
        println(message)
    }

    override fun onCleared() {
        disposable.clear()
    }
}