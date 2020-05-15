package com.app.kaidee.arch.mvi

import io.reactivex.functions.BiFunction

/**
 * Hold all [MviReducer] and choose with one should use depend on [MviResult]
 * */
interface MviReducerHolder<R : MviResult, S : MviViewState> : BiFunction<S, R, S>