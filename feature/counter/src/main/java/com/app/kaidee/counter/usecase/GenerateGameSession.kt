package com.app.kaidee.counter.usecase

import com.app.kaidee.counter.repository.CounterRepository
import io.reactivex.Observable
import javax.inject.Inject

class GenerateGameSession @Inject constructor(
	private val counterRepository: CounterRepository
) : () -> Observable<Pair<Int, Int>> {

	override fun invoke(): Observable<Pair<Int, Int>> {
		return Observable.fromCallable {
			counterRepository.generateGameSession()
		}
	}

}