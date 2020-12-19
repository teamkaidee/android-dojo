package com.app.kaidee.counter.navigation

import androidx.navigation.NavController
import com.app.kaidee.dojo.R

class CounterNavigatorImpl : CounterNavigator {

	lateinit var navController: NavController

	override fun navigateToResultPage() {
		navController.navigate(R.id.action_counter_to_result)
	}

	override fun navigateToLessonPage() {
		navController.popBackStack()
	}

}