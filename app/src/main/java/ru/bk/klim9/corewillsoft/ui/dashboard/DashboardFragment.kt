package ru.bk.klim9.corewillsoft.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_dashboard.*
import ru.bk.klim9.corewillsoft.R
import ru.bk.klim9.corewillsoft.database.content.*
import ru.bk.klim9.corewillsoft.utils.PreferenceUtil
import javax.inject.Inject

class DashboardFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: DashboardViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)
    }
    private lateinit var adapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (PreferenceUtil.isFirstStart) setHardcodedValues()
        initUi()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }

    private fun initUi() {
        adapter = DashboardAdapter(object : DashboardAdapter.Action {
            override fun deleteClick(transaction: Transaction) {
                viewModel.deleteTransaction(transaction)
            }

        })
        fdTransactionsRv.adapter = adapter
        fdTransactionsRv.layoutManager = LinearLayoutManager(context)
    }

    private fun initObservers() {
        viewModel.transactionsLd.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

    private fun setHardcodedValues() {
        val accounts = ArrayList<Account>()
        for (value in Accounts.values()) {
            accounts.add(Account(value.account))
        }
        val incomes = ArrayList<Income>()
        for (value in Incomes.values()) {
            incomes.add(Income(value.income))
        }
        val expenses = ArrayList<Expense>()
        for (value in Expenses.values()) {
            expenses.add(Expense(value.expense))
        }
        viewModel.saveHardcodedValue(accounts, incomes, expenses)
        PreferenceUtil.isFirstStart = false
    }
}