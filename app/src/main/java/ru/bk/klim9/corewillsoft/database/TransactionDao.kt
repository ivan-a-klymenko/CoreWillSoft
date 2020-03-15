package ru.bk.klim9.corewillsoft.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import ru.bk.klim9.corewillsoft.database.content.Account
import ru.bk.klim9.corewillsoft.database.content.Expense
import ru.bk.klim9.corewillsoft.database.content.Income
import ru.bk.klim9.corewillsoft.database.content.Transaction

/**
 * @author ivan.a.klymenko@gmail.com
 */
@Dao
interface TransactionDao {

    @Query("SELECT * FROM accounts")
    fun flowAccounts(): Flowable<List<Account>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllAccounts(accounts: List<Account>) : Completable


    @Query("SELECT * FROM incomes")
    fun flowIncomes(): Flowable<List<Income>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllIncomes(incomes: List<Income>) : Completable


    @Query("SELECT * FROM expenses")
    fun flowExpenses(): Flowable<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllExpenses(incomes: List<Expense>) : Completable


    @Query("SELECT * FROM transactions ORDER BY id DESC")
    fun flowTransactions(): Flowable<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTransaction(transaction: Transaction): Completable

    @Delete
    fun clearTransactions(transaction: Transaction): Completable
}
