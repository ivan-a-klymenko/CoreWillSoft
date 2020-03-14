package ru.bk.klim9.corewillsoft.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ru.bk.klim9.corewillsoft.ui.dashboard.DashboardFragment
import ru.bk.klim9.corewillsoft.ui.dashboard.DashboardViewModel

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
@Suppress("unused")
@Module
abstract class DashboardModule {

    @ContributesAndroidInjector
    abstract fun contributesDashboardFragment(): DashboardFragment

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindsDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel
}