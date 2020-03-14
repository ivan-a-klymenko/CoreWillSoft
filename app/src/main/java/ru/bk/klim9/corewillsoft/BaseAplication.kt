package ru.bk.klim9.corewillsoft

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
abstract class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initDataBase()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return null
    }

    protected abstract fun initDataBase()
}