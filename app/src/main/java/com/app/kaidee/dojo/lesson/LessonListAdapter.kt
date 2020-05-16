package com.app.kaidee.dojo.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.kaidee.dojo.R
import kotlinx.android.synthetic.main.item_lesson.view.*

class LessonListAdapter : ListAdapter<Lesson, LessonListAdapter.LessonViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(lesson: Lesson) {
            with(itemView) {
                title.text = lesson.title
                description.text = lesson.description
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