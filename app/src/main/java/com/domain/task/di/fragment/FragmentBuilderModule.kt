package com.domain.task.di.fragment

import com.domain.task.news.ui.fragments.BookMarkFragment
import com.domain.task.news.ui.fragments.DetailsFragment
import com.domain.task.news.ui.fragments.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module()
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector()
    abstract fun contributeNewsFragment(): NewsFragment

    @ContributesAndroidInjector()
    abstract fun contributeBookMarkFragment(): BookMarkFragment

    @ContributesAndroidInjector()
    abstract fun contributeNewsDetailsFragment(): DetailsFragment



}