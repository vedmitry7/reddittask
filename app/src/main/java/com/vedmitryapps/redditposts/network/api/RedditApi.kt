package com.vedmitryapps.redditposts.network.api

import com.vedmitryapps.redditposts.network.response.PostsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("r/politics.json")
    fun getCharacters(
        @Query("after") after: String?
    ): Deferred<Response<PostsResponse>>

}