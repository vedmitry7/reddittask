package com.vedmitryapps.redditposts.di

import com.vedmitryapps.redditposts.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import com.vedmitryapps.redditposts.di.module.DataModule
import com.vedmitryapps.redditposts.di.module.MainActivityModule
import com.vedmitryapps.redditposts.di.module.ViewModelModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        DataModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}