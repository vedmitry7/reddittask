package com.vedmitryapps.redditposts.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vedmitryapps.redditposts.data.database.model.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll(): List<Post>

    @Query("SELECT * FROM post WHERE id IN (:postIds)")
    fun loadAllByIds(postIds: LongArray): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Post) : Long

    @Query("DELETE FROM post WHERE id = :postId")
    fun deleteById(postId: String)
}