package com.vedmitryapps.redditposts.presentation.main.fragment.feed

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vedmitryapps.redditposts.R
import com.vedmitryapps.redditposts.base.fragment.BaseFragment
import com.vedmitryapps.redditposts.network.response.Children
import com.vedmitryapps.redditposts.presentation.main.MainViewModel
import com.vedmitryapps.redditposts.presentation.main.fragment.feed.support.OnClickListener
import com.vedmitryapps.redditposts.presentation.main.fragment.feed.support.PostAdapter
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : BaseFragment() {

    companion object {
        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }

    lateinit var mainViewModel: MainViewModel
    lateinit var adapter : PostAdapter
    lateinit var layoutManager : LinearLayoutManager
    var allItems = mutableListOf<Children>()
    var isFilterOn = false


    val postObserver = Observer<List<Children>?> {
        if(it == null) return@Observer
        allItems.addAll(it)
        adapter.update(allItems)
    }

    fun getFilteredItems(filter: String) = allItems.filter { it.data.title.contains(filter) }


    val updateAdapterObserver = Observer<Boolean?> {
        it?.let {
            adapter.notifyDataSetChanged()
            mainViewModel.updateAdapterLive.value = null
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)
        setupViews()

        mainViewModel.newChildrenItems.observe(this, postObserver)
        mainViewModel.updateAdapterLive.observe(this, updateAdapterObserver)
        mainViewModel.loadMorePosts()

        edit_text_search_FF.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s == null) return
                if(s.length >= 3) {
                    isFilterOn = true
                    adapter.update(getFilteredItems(s.toString().trim()))
                } else {
                    isFilterOn = false
                    adapter.update(allItems)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }


    val onClickListener = object : OnClickListener {

        override fun onSaveClick(item: Children) = mainViewModel.saveToFavorite(item)

        override fun onRemoveClick(postId: String) = mainViewModel.removeFromFavorite(postId)

        override fun isItemSaved(itemId: String) = mainViewModel.isItemSaved(itemId)
    }

    private fun setupViews() {
        adapter = PostAdapter(onClickListener)
        layoutManager = LinearLayoutManager(context)
        recycler_view_FF.layoutManager = layoutManager
        recycler_view_FF.adapter = adapter

        recycler_view_FF.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mainViewModel.progressLive.value ?: true) return
                if(isFilterOn) return
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val lastVisibleItems: Int = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItems + visibleItemCount >= totalItemCount) {
                    mainViewModel.loadMorePosts()
                }
            }
        })
    }

}