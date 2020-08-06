package com.vedmitryapps.redditposts.data.source

import com.vedmitryapps.redditposts.network.response.PostsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface RedditSource {

    fun getPosts(after: String?) : Deferred<Response<PostsResponse>>

}