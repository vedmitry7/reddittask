package com.vedmitryapps.redditposts.data.repository

import com.vedmitryapps.redditposts.data.source.RedditSource
import com.vedmitryapps.redditposts.network.api.RedditApi
import com.vedmitryapps.redditposts.network.response.PostsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class RedditRepository (val redditApi: RedditApi) : RedditSource {

    override fun getPosts(after: String?): Deferred<Response<PostsResponse>> {
        return redditApi.getCharacters(after)
    }
}