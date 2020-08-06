package com.vedmitryapps.redditposts.di.module


import com.vedmitryapps.redditposts.presentation.main.MainActivity
import com.vedmitryapps.redditposts.presentation.main.fragment.favorite.FavoriteFragment
import com.vedmitryapps.redditposts.presentation.main.fragment.feed.FeedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}


@Module
abstract class MainActivityFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeFeedFragment(): FeedFragment
    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment
}