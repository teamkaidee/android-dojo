package com.app.kaidee.dojo.lesson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.kaidee.dojo.databinding.ItemLessonBinding

class LessonListAdapter : ListAdapter<Lesson, LessonListAdapter.LessonViewHolder>(DiffCallBack()) {

	var onItemClickListener: ((Int) -> Unit)? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
		val binding = ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return LessonViewHolder(binding)
	}

	override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	inner class LessonViewHolder(private val binding: ItemLessonBinding) : RecyclerView.ViewHolder(binding.root) {

		fun bind(lesson: Lesson) {
			with(binding) {
				title.text = lesson.title
				description.text = lesson.description
				root.setOnClickListener {
					onItemClickListener?.invoke(lesson.navigationId)
				}
			}
		}

	}

	class DiffCallBack : DiffUtil.ItemCallback<Lesson>() {

		override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
			return oldItem == newItem
		}

		override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
			return oldItem == newItem
		}

	}

}