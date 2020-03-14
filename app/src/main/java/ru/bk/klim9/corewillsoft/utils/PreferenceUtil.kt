package ru.bk.klim9.corewillsoft.utils

import com.orhanobut.hawk.Hawk

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
object PreferenceUtil {

    private const val FIRST_START = "first_start"

    var isFirstStart: Boolean
        get() = Hawk.get(FIRST_START, true)
        set(value) {
            Hawk.put(FIRST_START, value)
        }
}