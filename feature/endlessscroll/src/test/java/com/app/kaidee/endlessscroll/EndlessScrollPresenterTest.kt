package com.app.kaidee.endlessscroll

import com.app.kaidee.common.rxscheduler.TestSchedulerProvider
import com.app.kaidee.endlessscroll.data.SimpleItem
import com.app.kaidee.endlessscroll.data.SimpleItemRepository
import com.app.kaidee.endlessscroll.presentation.EndlessScrollIntent
import com.app.kaidee.endlessscroll.presentation.EndlessScrollPresenter
import com.app.kaidee.endlessscroll.presentation.EndlessScrollViewState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EndlessScrollPresenterTest {

    @Mock
    lateinit var repository: SimpleItemRepository

    @Spy
    private var schedulerProvider = TestSchedulerProvider()

    @InjectMocks
    lateinit var presenter: EndlessScrollPresenter

    private lateinit var viewStateObserver: TestObserver<EndlessScrollViewState>

    @Before
    fun setUp() {
        viewStateObserver = presenter.states().test()
    }

    @Test
    fun `When initial page success`() {
        // GIVEN
        val items = makeSimpleItems()
        val idleViewState = EndlessScrollViewState.idle()
        val expectedViewState = idleViewState.copy(
            items = items,
            error = null,
            offset = 1
        )
        stubRepository(Observable.just(items))

        // WHEN
        presenter.dispatch(EndlessScrollIntent.InitialIntent)

        // THEN
        with(viewStateObserver) {
            assertValueAt(0, idleViewState)
            assertValueAt(1, expectedViewState)
        }
    }

    @Test
    fun `When initial page failure`() {
        // GIVEN
        val throwable = Exception()
        val idleViewState = EndlessScrollViewState.idle()
        val expectedViewState = idleViewState.copy(
            error = throwable,
            offset = 0,
            items = emptyList()
        )
        stubRepository(Observable.error(throwable))

        // WHEN
        presenter.dispatch(EndlessScrollIntent.InitialIntent)

        // THEN
        with(viewStateObserver) {
            assertValueAt(0, idleViewState)
            assertValueAt(1, expectedViewState)
        }
    }

    @Test
    fun `When load more success`() {
        // GIVEN
        val items = makeSimpleItems()
        val moreItems = makeSimpleItems()
        val idleViewState = EndlessScrollViewState.idle()
        val initialViewState = idleViewState.copy(
            items = items,
            error = null,
            offset = 1
        )
        val expectedViewState = initialViewState.copy(
            items = items + moreItems,
            offset = 2
        )
        whenever(repository.getItems(eq(0), any())).doReturn(Observable.just(items))
        whenever(repository.getItems(eq(1), any())).doReturn(Observable.just(moreItems))

        // WHEN
        presenter.dispatch(EndlessScrollIntent.InitialIntent)
        presenter.dispatch(EndlessScrollIntent.LoadMoreIntent)

        // THEN
        with(viewStateObserver) {
            assertValueAt(0, idleViewState)
            assertValueAt(1, initialViewState)
            assertValueAt(2, expectedViewState)
        }
    }

    private fun stubRepository(observable: Observable<List<SimpleItem>>) {
        whenever(repository.getItems(any(), any())).doReturn(observable)
    }

    private fun makeSimpleItems(): List<SimpleItem> {
        return listOf(SimpleItem(""), SimpleItem(""))
    }

}