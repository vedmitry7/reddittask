package com.vedmitryapps.redditposts.base.activity

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.vedmitryapps.redditposts.di.Injectable
import javax.inject.Inject


abstract class BaseActivity : FragmentActivity(), Injectable {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

}