package ru.bk.klim9.corewillsoft.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.bk.klim9.corewillsoft.database.content.Account
import ru.bk.klim9.corewillsoft.database.content.Expense
import ru.bk.klim9.corewillsoft.database.content.Income
import ru.bk.klim9.corewillsoft.database.content.Transaction

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
@Database(
    entities = [Transaction::class,
        Income::class,
        Expense::class,
        Account::class], version = 1, exportSchema = false
)
abstract class TransactionDatabase : RoomDatabase() {
    abstract fun moviesDao(): TransactionDao
}
