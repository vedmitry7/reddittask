package com.vedmitryapps.redditposts.presentation.main.fragment.favorite.support

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.vedmitryapps.redditposts.R
import com.vedmitryapps.redditposts.base.adapter.BaseRecyclerAdapter
import com.vedmitryapps.redditposts.base.adapter.BaseViewHolder
import com.vedmitryapps.redditposts.data.database.model.Post

class DatabasePostAdapter(val onRemoveClickListener: OnRemoveClickListener) : BaseRecyclerAdapter<Post, DatabasePostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatabasePostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return DatabasePostViewHolder(
            view,
            onRemoveClickListener
        )
    }

    override fun onBindViewHolder(holder: DatabasePostViewHolder, position: Int) {
        if(position == items.size) return
        holder.onBind(items[position])
    }

    fun updateItems(list: List<Post>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}

class DatabasePostViewHolder(itemView: View, val onRemoveClickListener: OnRemoveClickListener) : BaseViewHolder<Post>(itemView) {

    private val name: TextView? = itemView.findViewById(R.id.text_view_name_CVH)
    private val image: SimpleDraweeView? = itemView.findViewById(R.id.text_view_image_CVH)
    private val button: Button? = itemView.findViewById(R.id.button_CVH)

    override fun initUI() {
        name?.text = item.title

        button?.text = "remove"
        if(item.image=="self") image?.visibility = View.GONE
        else {
            image?.setImageURI(item.image)
            image?.visibility = View.VISIBLE
        }

        button?.setOnClickListener {
            onRemoveClickListener?.onRemoveClick(item.id)
        }
    }
}

interface OnRemoveClickListener {
    fun onRemoveClick(postId: String)
}