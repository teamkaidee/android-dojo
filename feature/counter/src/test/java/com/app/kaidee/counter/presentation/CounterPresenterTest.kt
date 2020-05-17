package com.app.kaidee.counter.presentation

import com.app.kaidee.common.rxscheduler.TestSchedulerProvider
import com.app.kaidee.counter.presentation.processor.GenerateGoalProcessor
import com.app.kaidee.counter.presentation.processor.UpdateValueProcessor
import com.app.kaidee.counter.presentation.reducer.GenerateGoalReducer
import com.app.kaidee.counter.presentation.reducer.UpdateValueReducer
import com.app.kaidee.counter.repository.CounterRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CounterPresenterTest {

    @Mock
    lateinit var counterRepository: CounterRepository

    private lateinit var presenter: CounterPresenter

    private lateinit var stateObserver: TestObserver<CounterViewState>

    private lateinit var navigationObserver: TestObserver<CounterRouter>

    @Before
    fun setUp() {
        val generateGoalProcessor = GenerateGoalProcessor(counterRepository)
        val updateValueProcessor = UpdateValueProcessor(counterRepository)
        val processorHolder = CounterProcessorHolder(generateGoalProcessor, updateValueProcessor)
        val reducerHolder = CounterReducerHolder(GenerateGoalReducer(), UpdateValueReducer())
        presenter = CounterPresenter(
            initialState = CounterViewState.idle(),
            schedulerProvider = TestSchedulerProvider(),
            processorHolder = processorHolder,
            reducerHolder = reducerHolder,
            actionMapper = CounterActionMapper(),
            routerMapper = CounterRouterMapper()
        )
        stateObserver = presenter.states().test()
        navigationObserver = presenter.navigation().test()
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
        navigationObserver.assertValue(CounterRouter.Stay)
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

        // WHEN
        presenter.dispatch(CounterIntent.InitialIntent)
        presenter.dispatch(CounterIntent.IncreaseIntent)

        // THEN
        stateObserver.assertValueAt(0, CounterViewState.idle())
        stateObserver.assertValueAt(1, initViewState)
        stateObserver.assertValueAt(2, expectedViewState)
        navigationObserver.assertValues(CounterRouter.Stay, CounterRouter.Stay)
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

        // WHEN
        presenter.dispatch(CounterIntent.InitialIntent)
        presenter.dispatch(CounterIntent.DecreaseIntent)

        // THEN
        stateObserver.assertValueAt(0, CounterViewState.idle())
        stateObserver.assertValueAt(1, initViewState)
        stateObserver.assertValueAt(2, expectedViewState)
        navigationObserver.assertValues(CounterRouter.Stay, CounterRouter.Stay)
    }

    @Test
    fun `When win should navigate to Result Page`() {
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
            count = startNumber + 1
        )
        stubGenerateGameSession(goal, startNumber)
        whenever(counterRepository.isWin(any())).doReturn(true)

        // WHEN
        presenter.dispatch(CounterIntent.InitialIntent)
        presenter.dispatch(CounterIntent.IncreaseIntent)

        // THEN
        stateObserver.assertValueAt(0, CounterViewState.idle())
        stateObserver.assertValueAt(1, initViewState)
        stateObserver.assertValueAt(2, expectedViewState)
        navigationObserver.assertValues(CounterRouter.Stay, CounterRouter.ResultPage)
    }

    private fun stubGenerateGameSession(goal: Int, startNumber: Int) {
        whenever(counterRepository.generateGameSession()).doReturn(Pair(goal, startNumber))
    }

}