package ru.bk.klim9.corewillsoft

import com.orhanobut.hawk.Hawk
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ru.bk.klim9.corewillsoft.di.ApplicationComponent
import ru.bk.klim9.corewillsoft.di.DaggerApplicationComponent
import ru.bk.klim9.movies.database.DatabaseHolder

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
class CwsApplication : BaseApplication() {

    lateinit var instance: CwsApplication
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        applicationComponent = DaggerApplicationComponent.builder().application(this).build()
        instance = this
        super.onCreate()
        Hawk.init(this).build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return applicationComponent
    }

    override fun initDataBase() {
        DatabaseHolder.init(this)
    }
}