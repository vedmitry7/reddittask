package com.vedmitryapps.redditposts.presentation.main.fragment.feed.support

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.vedmitryapps.redditposts.R
import com.vedmitryapps.redditposts.base.adapter.BaseRecyclerAdapter
import com.vedmitryapps.redditposts.base.adapter.BaseViewHolder
import com.vedmitryapps.redditposts.network.response.Children

class PostAdapter(val onClickListener: OnClickListener) : BaseRecyclerAdapter<Children, PostViewHolder>() {

    companion object {
        const val VIEW_TYPE_POST = 1
        const val VIEW_TYPE_PROGRESS = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = if(viewType == VIEW_TYPE_POST)
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        else
            LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
        return PostViewHolder(view, onClickListener)
    }

    override fun getItemCount() = items.size +1

    override fun getItemViewType(position: Int): Int {
        return if(position == itemCount -1) VIEW_TYPE_PROGRESS
        else VIEW_TYPE_POST
    }

    fun update(list: List<Children>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        if(position == items.size) return
        holder.onBind(items[position])
    }
}

class PostViewHolder(itemView: View, val onClickListener: OnClickListener) : BaseViewHolder<Children>(itemView) {

    private val name: TextView? = itemView.findViewById(R.id.text_view_name_CVH)
    private val image: SimpleDraweeView? = itemView.findViewById(R.id.text_view_image_CVH)
    private val button: Button? = itemView.findViewById(R.id.button_CVH)

    override fun initUI() {
        name?.text = item.data.title

        if(item.data.thumbnail=="self") image?.visibility = View.GONE
        else {
            image?.setImageURI(item.data.thumbnail)
            image?.visibility = View.VISIBLE
        }

        button?.setOnClickListener {
            if(onClickListener.isItemSaved(item.data.id))
                onClickListener?.onRemoveClick(item.data.id)
            else onClickListener?.onSaveClick(item)
        }

        updateButtonText()
    }

    fun updateButtonText() {
        button?.text = if(onClickListener.isItemSaved(item.data.id)) "remove" else "save"
    }
}

interface OnClickListener {
    fun onSaveClick(item: Children)
    fun onRemoveClick(postId: String)
    fun isItemSaved(itemId: String) : Boolean
}