package ru.bk.klim9.corewillsoft.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.bk.klim9.corewillsoft.CwsApplication
import ru.bk.klim9.corewillsoft.di.modules.DaggerViewModelInjectionModule
import ru.bk.klim9.corewillsoft.di.modules.DashboardModule
import ru.bk.klim9.corewillsoft.di.modules.RepositoryModule
import ru.bk.klim9.corewillsoft.di.modules.TransactionsModule
import javax.inject.Singleton

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        DaggerViewModelInjectionModule::class,
        RepositoryModule::class,
        DashboardModule::class,
        TransactionsModule::class]
)
interface ApplicationComponent : AndroidInjector<CwsApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}