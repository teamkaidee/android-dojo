package com.app.kaidee.arch.mvi

/**
 * [MviReducer] is responsible to generate the [MviViewState] which the [MviView] will use to render itself.
 * [MviView] should be stateless in the sense that the [MviViewState] should be sufficient for the rendering.
 * [MviReducer] takes the latest [MviViewState] available,
 * apply the latest [MviResult] to it and return a whole new [MviViewState].
 * */
interface MviReducer<R : MviResult, S : MviViewState> {

	fun reduce(result: R, previousState: S): S

}