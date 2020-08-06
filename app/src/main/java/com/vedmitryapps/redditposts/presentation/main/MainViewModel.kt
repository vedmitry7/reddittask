package com.vedmitryapps.redditposts.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vedmitryapps.redditposts.data.database.model.Post
import com.vedmitryapps.redditposts.data.source.DatabaseSource
import com.vedmitryapps.redditposts.data.source.RedditSource
import com.vedmitryapps.redditposts.network.response.Children
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(val redditSource: RedditSource,
                                        val databaseSource: DatabaseSource) : ViewModel() {

    val newChildrenItems = MutableLiveData<List<Children>?>()
    val databaseItems = MutableLiveData<List<Post>?>()
    val after = MutableLiveData<String?>()
    val progressLive = MutableLiveData<Boolean>()
    val updateAdapterLive = MutableLiveData<Boolean?>()

    fun loadMorePosts() {
        progressLive.postValue(true)
        viewModelScope.launch {
            val response = redditSource.getPosts(after.value).await()
            if (response.isSuccessful) {
                response.body()?.let {
                    after.value = it.data.after
                    newChildrenItems.postValue(it.data.children)
                    progressLive.postValue(false)
                }
            }
        }
    }

    fun loadSavedPosts() {
        viewModelScope.launch (Dispatchers.IO) {
            databaseItems.postValue(databaseSource.getPosts())
            updateAdapterLive.postValue(true)
        }
    }

    fun saveToFavorite(item: Children) {
        viewModelScope.launch (Dispatchers.IO) {
            val sr =  databaseSource.insert(Post.getInstanceByChildren(item))
            loadSavedPosts()
        }
    }

    fun removeFromFavorite(itemId: String) {
        viewModelScope.launch (Dispatchers.IO) {
            databaseSource.delete(itemId)
            loadSavedPosts()
        }
    }

    fun isItemSaved(itemId: String) : Boolean {
        return databaseItems.value?.map { it.id }?.contains(itemId) ?: false
    }
}