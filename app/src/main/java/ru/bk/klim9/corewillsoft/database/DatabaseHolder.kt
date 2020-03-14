package ru.bk.klim9.movies.database

import android.content.Context

import androidx.annotation.MainThread
import androidx.room.Room
import ru.bk.klim9.corewillsoft.database.TransactionDatabase

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
object DatabaseHolder {

    private lateinit var database: TransactionDatabase

    @MainThread
    fun init(context: Context) {
        database = Room.databaseBuilder(
            context.applicationContext,
            TransactionDatabase::class.java,
            "movies-database"
        ).build()
    }

    fun database(): TransactionDatabase {
        return database
    }
}
