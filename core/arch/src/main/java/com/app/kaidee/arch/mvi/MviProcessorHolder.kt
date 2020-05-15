package com.app.kaidee.arch.mvi

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

/**
 * The gateway to control which [MviProcessor] should be execute depend on [MviAction]
 * */
abstract class MviProcessorHolder<A : MviAction, R : MviResult> : ObservableTransformer<A, R> {

    protected abstract fun compose(observable: Observable<A>): Observable<R>

    protected abstract fun isAcceptAction(action: A): Boolean

    protected fun errorComposer(observer: Observable<A>): Observable<R> {
        return observer.filter { action ->
            !isAcceptAction(action)
        }.flatMap { action ->
            Observable.error<R>(IllegalArgumentException("Unknown Action type: $action"))
        }
    }

    override fun apply(upstream: Observable<A>): ObservableSource<R> {
        return upstream.publish(::compose)
    }

}