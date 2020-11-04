package com.app.kaidee.endlessscroll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.kaidee.endlessscroll.data.SimpleItem

class SimpleDataListAdapter :
	ListAdapter<SimpleItem, RecyclerView.ViewHolder>(AsyncDifferConfig.Builder(SimpleItemDiffCallback()).build()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_simple_data, parent, false)
		return SimpleDataViewHolder(view)
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		(holder as? SimpleDataViewHolder)?.bind(getItem(position))
	}

	class SimpleDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		fun bind(data: SimpleItem) {
			itemView.findViewById<TextView>(R.id.textview_display_text)?.text = data.title
		}

	}

	class SimpleItemDiffCallback : DiffUtil.ItemCallback<SimpleItem>() {

		override fun areItemsTheSame(oldItem: SimpleItem, newItem: SimpleItem): Boolean {
			return oldItem.title == newItem.title
		}

		override fun areContentsTheSame(oldItem: SimpleItem, newItem: SimpleItem): Boolean {
			return oldItem == newItem
		}

	}

}