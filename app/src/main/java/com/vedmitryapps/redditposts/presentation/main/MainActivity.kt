package com.vedmitryapps.redditposts.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vedmitryapps.redditposts.R
import com.vedmitryapps.redditposts.base.activity.BaseActivity
import com.vedmitryapps.redditposts.di.Injectable
import com.vedmitryapps.redditposts.presentation.main.fragment.favorite.FavoriteFragment
import com.vedmitryapps.redditposts.presentation.main.fragment.feed.FeedFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), Injectable, HasSupportFragmentInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var pagerAdapter: MainFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = MainFragmentAdapter(supportFragmentManager)
        val favoriteFragment = FavoriteFragment.newInstance()
        pagerAdapter.addFragment(FeedFragment.newInstance())
        pagerAdapter.addFragment(favoriteFragment)

        view_pager_MA.adapter = pagerAdapter
        tab_layout_MA.setupWithViewPager(view_pager_MA)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}

