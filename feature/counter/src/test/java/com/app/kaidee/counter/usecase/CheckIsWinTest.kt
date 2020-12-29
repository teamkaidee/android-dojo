package com.app.kaidee.counter.usecase

import com.app.kaidee.counter.repository.CounterRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CheckIsWinTest {

    @Mock
    lateinit var counterRepository: CounterRepository

    @InjectMocks
    lateinit var useCase: CheckIsWin

    @Test
    fun `When check is win success`() {
        // GIVEN
        val result = true
        whenever(counterRepository.isWin(any())).doReturn(result)

        // WHEN
        val testObserver = useCase(0).test()

        // THEN
        testObserver.assertValue(result)
    }

}