package com.app.kaidee.arch.mvi

/**
 * Object representing a UI that will
 * a) emit its intents to a view model,
 * b) subscribes to a view model for rendering its UI.
 *
 * @param I Top class of the [MviIntent] that the [MviView] will be emitting.
 * @param S Top class of the [MviViewState] the [MviView] will be subscribing to.
 */
interface MviView<I : MviIntent, in S : MviViewState> {

    /**
     * Entry point for the [MviView] to render itself based on a [MviViewState].
     */
    fun render(state: S)

}