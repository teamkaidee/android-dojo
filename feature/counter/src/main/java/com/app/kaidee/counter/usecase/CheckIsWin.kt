package com.app.kaidee.counter.usecase

import com.app.kaidee.counter.repository.CounterRepository
import io.reactivex.Observable
import javax.inject.Inject

class CheckIsWin @Inject constructor(
    private val counterRepository: CounterRepository
) : (Int) -> Observable<Boolean> {

    override fun invoke(value: Int): Observable<Boolean> {
        return Observable.fromCallable {
            counterRepository.isWin(value)
        }
    }

}