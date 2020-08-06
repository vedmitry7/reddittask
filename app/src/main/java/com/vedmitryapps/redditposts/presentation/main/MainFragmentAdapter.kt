package com.vedmitryapps.redditposts.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainFragmentAdapter(fragmentManager: FragmentManager)
    :  FragmentPagerAdapter(fragmentManager) {

    private val fragments = mutableListOf<Fragment>()

    fun addFragment(fragment: Fragment) = fragments.add(fragment)

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Feed"
            1 -> "Favorite"
            else -> ""
        }
    }

}