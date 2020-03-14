package ru.bk.klim9.corewillsoft.ui.transactions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.bk.klim9.corewillsoft.database.content.Account
import ru.bk.klim9.corewillsoft.database.content.Expense
import ru.bk.klim9.corewillsoft.database.content.Income
import ru.bk.klim9.corewillsoft.ui.common.BaseViewModel
import javax.inject.Inject

private const val TAG = "TransactionViewModel"

class TransactionViewModel @Inject constructor() : BaseViewModel() {

    val accountsLd = MutableLiveData<List<String>>()
    val expensesLd = MutableLiveData<List<String>>()
    val incomesLd = MutableLiveData<List<String>>()

    fun getData() {
        cd.add(repository.flowAccounts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val accountNames = ArrayList<String>()
                for (account in it) {
                    accountNames.add(account.accountName)
                }
                accountsLd.value = accountNames
            }, {
                Log.d(TAG, "flowAccounts error: ${it.message}", it)
            }))

        cd.add(repository.flowExpenses()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val expenseNames = ArrayList<String>()
                for (value in it) {
                    expenseNames.add(value.expenseName)
                }
                expensesLd.value = expenseNames
            }, {
                Log.d(TAG, "flowExpenses error: ${it.message}", it)
            }))

        cd.add(repository.flowIncomes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val incomeNames = ArrayList<String>()
                for (value in it) {
                    incomeNames.add(value.incomeName)
                }
                incomesLd.value = incomeNames
            }, {
                Log.d(TAG, "flowIncomes error: ${it.message}", it)
            }))
    }
}