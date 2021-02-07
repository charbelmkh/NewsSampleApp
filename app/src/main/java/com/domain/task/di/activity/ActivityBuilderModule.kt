package com.domain.task.di.activity


import com.domain.task.SingleActivity
import com.domain.task.di.fragment.FragmentBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module()
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [  FragmentBuilderModule::class ])
    abstract fun contributeSingleActivity(): SingleActivity

}