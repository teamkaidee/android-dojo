package com.app.kaidee.endlessscroll.presentation

import com.app.kaidee.arch.mvi.MviPresenter
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import javax.inject.Inject

class EndlessScrollPresenter @Inject constructor(
	schedulerProvider: SchedulerProvider,
	processorHolder: EndlessScrollProcessorHolder,
	reducerHolder: EndlessScrollReducerHolder,
	actionMapper: EndlessScrollActionMapper
) : MviPresenter<EndlessScrollIntent, EndlessScrollViewState, EndlessScrollAction, EndlessScrollResult>(
	EndlessScrollViewState.idle(),
	schedulerProvider,
	processorHolder,
	reducerHolder,
	actionMapper
)