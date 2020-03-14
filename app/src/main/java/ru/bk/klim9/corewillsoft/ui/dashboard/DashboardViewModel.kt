package ru.bk.klim9.corewillsoft.ui.dashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.bk.klim9.corewillsoft.database.content.Account
import ru.bk.klim9.corewillsoft.database.content.Expense
import ru.bk.klim9.corewillsoft.database.content.Income
import ru.bk.klim9.corewillsoft.ui.common.BaseViewModel
import java.util.ArrayList
import javax.inject.Inject

private const val TAG = "DashboardViewModel"

class DashboardViewModel @Inject constructor() : BaseViewModel() {

    val transactionsLd = MutableLiveData<List<DashboardAdapter.Item>>()

    fun getData() {
        cd.add(repository.flowTransactions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                transactionsLd.value = it
            }, {
                Log.d(TAG, "flowTransactions error: ${it.message}", it)
            }))
    }

    fun saveHardcodedValue(
        accounts: ArrayList<Account>,
        incomes: ArrayList<Income>,
        expenses: ArrayList<Expense>
    ) {
        cd.add(repository.saveHardcodedValues(accounts, incomes, expenses)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }

}