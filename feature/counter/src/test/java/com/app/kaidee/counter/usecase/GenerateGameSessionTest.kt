package com.app.kaidee.counter.usecase

import com.app.kaidee.counter.repository.CounterRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GenerateGameSessionTest {

	@Mock
	lateinit var counterRepository: CounterRepository

	@InjectMocks
	lateinit var userCase: GenerateGameSession

	@Test
	fun `When generate game session success`() {
		// GIVEN
		val result = Pair(0, 0)
		whenever(counterRepository.generateGameSession()).doReturn(result)

		// WHEN
		val testObserver = userCase().test()

		// THEN
		testObserver.assertValue(result)
	}

}