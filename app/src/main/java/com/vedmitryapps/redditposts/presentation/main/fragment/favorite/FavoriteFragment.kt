package com.vedmitryapps.redditposts.presentation.main.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vedmitryapps.redditposts.R
import com.vedmitryapps.redditposts.base.fragment.BaseFragment
import com.vedmitryapps.redditposts.data.database.model.Post
import com.vedmitryapps.redditposts.presentation.main.MainViewModel
import com.vedmitryapps.redditposts.presentation.main.fragment.favorite.support.DatabasePostAdapter
import com.vedmitryapps.redditposts.presentation.main.fragment.favorite.support.OnRemoveClickListener
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment() {

    companion object {
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }

    lateinit var mainViewModel: MainViewModel
    lateinit var adapter : DatabasePostAdapter
    lateinit var layoutManager : LinearLayoutManager

    val postObserver = Observer<List<Post>?> {
        it?.let {list -> adapter.updateItems(list) }
    }

    val updateAdapterObserver = Observer<Boolean?> {
        it?.let {
            adapter.notifyDataSetChanged()
            mainViewModel.updateAdapterLive.value = null
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        setupViews()

        mainViewModel.databaseItems.observe(this, postObserver)

        mainViewModel.loadSavedPosts()
    }

    val onRemoveClickListener = object :
        OnRemoveClickListener {
        override fun onRemoveClick(postId: String) {
            mainViewModel.removeFromFavorite(postId)
        }
    }

    private fun setupViews() {
        adapter =
            DatabasePostAdapter(
                onRemoveClickListener
            )
        layoutManager = LinearLayoutManager(context)
        recycler_view_FF.layoutManager = layoutManager
        recycler_view_FF.adapter = adapter
    }
}