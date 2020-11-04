package com.app.kaidee.arch.mvi

/**
 * Immutable object which contains all the required information to render a [MviView].
 */
interface MviViewState {

	fun toLogString(): String

}
