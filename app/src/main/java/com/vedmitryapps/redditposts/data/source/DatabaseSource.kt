package com.vedmitryapps.redditposts.data.source

import com.vedmitryapps.redditposts.data.database.model.Post

interface DatabaseSource {

    fun getPosts() : List<Post>
    fun insert(post: Post) : Long
    fun delete(postId: String)

}