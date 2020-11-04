package com.app.kaidee.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_counter_result.*
import com.app.kaidee.dojo.R as appRes

class CounterResultFragment : Fragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_counter_result, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		button_play_again?.setOnClickListener {
			findNavController().navigate(appRes.id.action_result_to_counter)
		}
		button_lesson?.setOnClickListener {
			findNavController().navigate(appRes.id.action_result_to_lesson)
		}
	}

}
