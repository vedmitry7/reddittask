package com.vedmitryapps.redditposts.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.vedmitryapps.redditposts.base.fragment.BaseFragment


abstract class BaseFragmentPagerAdapter<F : BaseFragment>(fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = mutableListOf<F>()

    fun addFragment(fragment: F) = fragments.add(fragment)

    fun clear() = fragments.clear()

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getItemPosition(`object`: Any): Int {
        return if(fragments.contains(`object`as Fragment)){
            super.getItemPosition(`object`)
        }
        else {
            POSITION_NONE
        }
    }
}