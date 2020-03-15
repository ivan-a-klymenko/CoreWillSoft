package ru.bk.klim9.corewillsoft.repositoty

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import ru.bk.klim9.corewillsoft.database.TransactionDao
import ru.bk.klim9.corewillsoft.database.content.*
import ru.bk.klim9.corewillsoft.ui.dashboard.ACCOUNT
import ru.bk.klim9.corewillsoft.ui.dashboard.TRANSACTION
import ru.bk.klim9.corewillsoft.ui.dashboard.DashboardAdapter.*
import java.util.ArrayList

private const val TAG = "DataRepository"

class DataRepository(private val transactionDao: TransactionDao) {

    fun saveHardcodedValues(
        accounts: ArrayList<Account>,
        incomes: ArrayList<Income>,
        expenses: ArrayList<Expense>
    ) : Completable {
        return Completable.mergeArray(transactionDao.saveAllAccounts(accounts),
            transactionDao.saveAllIncomes(incomes),
            transactionDao.saveAllExpenses(expenses))
    }

    fun flowTransactions(): Flowable<List<Item>> {
        return Flowable.combineLatest(transactionDao.flowAccounts(),
            transactionDao.flowTransactions(),
            BiFunction { t1, t2 -> getItems(t1, t2) }
        )
    }

    private fun getItems(accounts: List<Account>, transactions: List<Transaction>): List<Item> {
        val result = ArrayList<Item>()
        for (account in accounts) {
            val accountList = sortForAccount(account, transactions)
            if (accountList.isNotEmpty()) result.addAll(accountList)
        }
        return result
    }

    private fun sortForAccount(account: Account, transactions: List<Transaction>): List<Item> {
        val accountSortResult = ArrayList<Item>()
        var balance = 0
        for (transaction in transactions) {
            if (account.accountName == transaction.accountName) {
                accountSortResult.add(Item(TRANSACTION, transaction, null))
                balance = when (transaction.transactionType) {
                    INCOME -> balance + transaction.amount!!
                    else -> balance - transaction.amount!!
                }
            }
        }
        if (accountSortResult.isNotEmpty()) {
            account.amount = balance
            accountSortResult.add(0, Item(ACCOUNT, null, account))
        }
        return accountSortResult
    }

    fun flowAccounts(): Flowable<List<Account>> {
        return transactionDao.flowAccounts()
    }

    fun flowIncomes(): Flowable<List<Income>> {
        return transactionDao.flowIncomes()
    }

    fun flowExpenses(): Flowable<List<Expense>> {
        return transactionDao.flowExpenses()
    }

    fun saveTransaction(transaction: Transaction): Completable {
        return transactionDao.saveTransaction(transaction)
    }

}
