package com.vedmitryapps.redditposts.base.adapter

import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerAdapter<I: Any, VH: BaseViewHolder<I>> : RecyclerView.Adapter<VH>() {

    val items = mutableListOf<I>()

    override fun getItemCount() = items.size

    fun addItems(items: List<I>) {
        //this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: I) {
        items.add(item)
        notifyItemChanged(items.size - 1)
    }
}