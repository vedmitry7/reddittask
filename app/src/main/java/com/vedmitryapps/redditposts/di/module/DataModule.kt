package com.vedmitryapps.redditposts.di.module

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vedmitryapps.redditposts.App
import com.vedmitryapps.redditposts.data.database.AppDatabase
import com.vedmitryapps.redditposts.data.repository.DatabaseRepository
import com.vedmitryapps.redditposts.data.repository.RedditRepository
import com.vedmitryapps.redditposts.data.source.DatabaseSource
import com.vedmitryapps.redditposts.data.source.RedditSource
import com.vedmitryapps.redditposts.network.api.RedditApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesDatabase(app: App): AppDatabase {
        return AppDatabase.getInstance(app.applicationContext)
    }

    @Provides
    fun provideDatabaseSource(appDatabase: AppDatabase) : DatabaseSource
            = DatabaseRepository(appDatabase)

    @Provides
    fun provideRedditSource(redditApi: RedditApi) : RedditSource
            = RedditRepository(redditApi)

    @Provides
    fun provideRedditApi(httpClient: OkHttpClient) =
        buildRetrofit("https://www.reddit.com/", httpClient)
            .create(RedditApi::class.java)


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    fun buildRetrofit(url: String, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(httpClient)
            .addConverterFactory(createGsonConverterFactory())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun createGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .create()
        return GsonConverterFactory.create(gson)
    }
}