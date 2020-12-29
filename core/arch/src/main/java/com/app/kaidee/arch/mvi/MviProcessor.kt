package com.app.kaidee.arch.mvi

import io.reactivex.ObservableTransformer

/**
 * [MviProcessor] simply executes an [MviAction]. Inside the [MviPresenter],
 * this is the only place where side-effects should happen: data writing, data reading, etc.
 * */
interface MviProcessor<A : MviAction, R : MviResult> {

    fun execute(): ObservableTransformer<A, R>

}