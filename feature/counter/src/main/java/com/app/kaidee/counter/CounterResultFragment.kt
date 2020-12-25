package com.app.kaidee.counter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.kaidee.counter.databinding.FragmentCounterResultBinding
import com.app.kaidee.dojo.R as appRes

class CounterResultFragment : Fragment(R.layout.fragment_counter_result) {

	private val navController by lazy {
		findNavController()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		with(FragmentCounterResultBinding.bind(view)) {
			buttonPlayAgain.setOnClickListener {
				navController.navigate(appRes.id.action_result_to_counter)
			}
			buttonLesson.setOnClickListener {
				navController.navigate(appRes.id.action_result_to_lesson)
			}
		}
	}

}
