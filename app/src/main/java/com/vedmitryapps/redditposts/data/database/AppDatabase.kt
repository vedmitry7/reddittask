package com.vedmitryapps.redditposts.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vedmitryapps.redditposts.data.database.dao.PostDao
import com.vedmitryapps.redditposts.data.database.model.Post

@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "database-name"
        fun getInstance(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .build()
    }

    abstract fun postDao(): PostDao

}