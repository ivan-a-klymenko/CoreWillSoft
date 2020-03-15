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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAccount(account: Account)

    @Delete
    fun clearAccounts(movies: List<Account>)

    @Query("SELECT * FROM accounts WHERE  accountName = :accountName")
    fun flowAccounts(accountName: String): Flowable<Account>


    @Query("SELECT * FROM incomes")
    fun flowIncomes(): Flowable<List<Income>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllIncomes(incomes: List<Income>) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveIncome(income: Income)

    @Delete
    fun clearIncomes(incomes: List<Income>)

    @Query("SELECT * FROM incomes WHERE  incomeName = :incomeName")
    fun flowIncomes(incomeName: String): Flowable<Income>


    @Query("SELECT * FROM expenses")
    fun flowExpenses(): Flowable<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllExpenses(incomes: List<Expense>) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveExpense(income: Expense) : Completable

    @Delete
    fun clearExpenses(incomes: List<Expense>)

    @Query("SELECT * FROM expenses WHERE  expenseName = :expenseName")
    fun flowExpenses(expenseName: String): Flowable<Expense>


    @Query("SELECT * FROM transactions")
    fun flowTransactions(): Flowable<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllTransactions(incomes: List<Transaction>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTransaction(transaction: Transaction): Completable

    @Delete
    fun clearTransactions(incomes: List<Transaction>)
}
