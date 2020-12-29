package com.app.kaidee.endlessscroll

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.kaidee.endlessscroll.data.SimpleItem
import com.app.kaidee.endlessscroll.databinding.ItemSimpleDataBinding

class SimpleDataListAdapter : ListAdapter<SimpleItem, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder(SimpleItemDiffCallback()).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemSimpleDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimpleDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? SimpleDataViewHolder)?.bind(getItem(position))
    }

    class SimpleDataViewHolder(private val binding: ItemSimpleDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: SimpleItem) {
            binding.textviewDisplayText.text = data.title
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