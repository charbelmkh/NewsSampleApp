package com.domain.task.di

import android.app.Application
import com.domain.task.App
import com.domain.task.di.activity.ActivityBuilderModule
import com.domain.task.di.module.AppModule
import com.domain.task.di.viewmodel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        ViewModelFactoryModule::class


    ]


)

interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}
