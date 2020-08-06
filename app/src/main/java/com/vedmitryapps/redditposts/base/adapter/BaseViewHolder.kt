package com.vedmitryapps.redditposts.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<T: Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var item: T

    fun onBind(item: T) {
        this.item = item
        initUI()
    }

    abstract fun initUI()
}