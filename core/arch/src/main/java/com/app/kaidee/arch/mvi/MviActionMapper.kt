package com.app.kaidee.arch.mvi

/**
 * Mapper class for translate [MviIntent] to [MviAction]
 * [MviIntent] and [MviAction] often look similar
 * but important to avoid data flow to be too couple with [MviView]
 * */
interface MviActionMapper<in I : MviIntent, out A : MviAction> {

    fun mapToAction(intent: I): A

}