package com.app.kaidee.counter.presentation

import com.app.kaidee.common.rxscheduler.TestSchedulerProvider
import com.app.kaidee.counter.navigation.CounterNavigator
import com.app.kaidee.counter.usecase.CheckIsWin
import com.app.kaidee.counter.usecase.GenerateGameSession
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CounterPresenterTest {

	@Mock
	lateinit var generateGameSession: GenerateGameSession

	@Mock
	lateinit var checkIsWin: CheckIsWin

	@Mock
	lateinit var navigator: CounterNavigator

	private lateinit var presenter: CounterPresenter

	private lateinit var stateObserver: TestObserver<CounterViewState>

	@Before
	fun setUp() {
		presenter = CounterPresenter(
			initialState = CounterViewState.idle(),
			schedulerProvider = TestSchedulerProvider(),
			generateGameSession = generateGameSession,
			checkIsWin = checkIsWin,
			navigator = navigator
		)
		stateObserver = presenter.states().test()
	}

	@Test
	fun `When initial view should generate goal and start number`() {
		// GIVEN
		val goal = 1
		val startNumber = 2
		val expectedViewState = CounterViewState.idle().copy(
			isLoading = false,
			goal = goal,
			count = startNumber
		)
		stubGenerateGameSession(goal, startNumber)

		// WHEN
		presenter.dispatch(CounterIntent.InitialIntent)

		// THEN
		stateObserver.assertValueAt(0, CounterViewState.idle())
		stateObserver.assertValueAt(1, expectedViewState)
	}

	@Test
	fun `When press increase button count should increase by 1`() {
		// GIVEN
		val goal = 1
		val startNumber = 2
		val initViewState = CounterViewState.idle().copy(
			isLoading = false,
			goal = goal,
			count = startNumber,
			error = null
		)
		val expectedViewState = initViewState.copy(
			count = startNumber + 1
		)
		stubGenerateGameSession(goal, startNumber)
		stubCheckIsWin(false)

		// WHEN
		presenter.dispatch(CounterIntent.InitialIntent)
		presenter.dispatch(CounterIntent.IncreaseIntent)

		// THEN
		stateObserver.assertValueAt(0, CounterViewState.idle())
		stateObserver.assertValueAt(1, initViewState)
		stateObserver.assertValueAt(2, expectedViewState)
	}

	@Test
	fun `When press decrease button count should decrease by 1`() {
		// GIVEN
		val goal = 2
		val startNumber = 1
		val initViewState = CounterViewState.idle().copy(
			isLoading = false,
			goal = goal,
			count = startNumber,
			error = null
		)
		val expectedViewState = initViewState.copy(
			count = startNumber - 1
		)
		stubGenerateGameSession(goal, startNumber)
		stubCheckIsWin(false)

		// WHEN
		presenter.dispatch(CounterIntent.InitialIntent)
		presenter.dispatch(CounterIntent.DecreaseIntent)

		// THEN
		stateObserver.assertValueAt(0, CounterViewState.idle())
		stateObserver.assertValueAt(1, initViewState)
		stateObserver.assertValueAt(2, expectedViewState)
	}

	@Test
	fun `When win should navigate to Result Page`() {
		// GIVEN
		val goal = 2
		val startNumber = 1
		val expectedViewState = CounterViewState.idle().copy(
			isLoading = false,
			goal = goal,
			count = startNumber,
			error = null
		)
		stubGenerateGameSession(goal, startNumber)
		stubCheckIsWin(true)

		// WHEN
		presenter.dispatch(CounterIntent.InitialIntent)
		presenter.dispatch(CounterIntent.IncreaseIntent)

		// THEN
		stateObserver.assertValueAt(0, CounterViewState.idle())
		stateObserver.assertValueAt(1, expectedViewState)
		verify(navigator).navigateToResultPage()
	}

	private fun stubGenerateGameSession(goal: Int, startNumber: Int) {
		whenever(generateGameSession.invoke()).doReturn(Observable.just(Pair(goal, startNumber)))
	}

	private fun stubCheckIsWin(isWin: Boolean) {
		whenever(checkIsWin(any())).doReturn(Observable.just(isWin))
	}

}