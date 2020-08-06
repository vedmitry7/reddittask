package com.vedmitryapps.redditposts.data.repository

import com.vedmitryapps.redditposts.data.database.AppDatabase
import com.vedmitryapps.redditposts.data.database.model.Post
import com.vedmitryapps.redditposts.data.source.DatabaseSource

class DatabaseRepository (val database: AppDatabase) : DatabaseSource {

    override fun getPosts(): List<Post> {
        return database.postDao().getAll()
    }

    override fun insert(post: Post): Long {
        return database.postDao().insert(post)
    }

    override fun delete(postId: String) {
        database.postDao().deleteById(postId)
    }
}