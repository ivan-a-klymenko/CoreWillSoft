package ru.bk.klim9.corewillsoft.ui.transactions

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_transaction.*
import ru.bk.klim9.corewillsoft.R
import ru.bk.klim9.corewillsoft.database.content.EXPENSE
import ru.bk.klim9.corewillsoft.database.content.INCOME
import ru.bk.klim9.corewillsoft.database.content.Transaction
import javax.inject.Inject


class TransactionFragment : Fragment() {

    private var isIncome: Boolean = true

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: TransactionViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TransactionViewModel::class.java)
    }
    private var incomes: List<String>? = null
    private var expenses: List<String>? = null
    private var accounts: List<String>? = null
    private var account: String? = null
    private var income: String? = null
    private var expense: String? = null
    private val transaction: Transaction = Transaction()
    private val categoryAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, ArrayList<String>())
    }
    private val accountAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, ArrayList<String>())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initUi()
    }

    private fun initUi() {
        viewModel.getData()
        ftAccountSp.adapter = accountAdapter
        ftAccountSp.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View,
                position: Int, id: Long
            ) {
                transaction.accountName = accounts?.get(position)
                ftAccountTitleTv.visibility = View.GONE
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }

        ftCategorySp.adapter = categoryAdapter
        ftCategorySp.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View,
                position: Int, id: Long
            ) {
                if (isIncome) {
                    transaction.transactionName = incomes?.get(position)
                    transaction.transactionType = INCOME
                } else {
                    transaction.transactionName = expenses?.get(position)
                    transaction.transactionType = EXPENSE
                }
                ftCategoryTitleTv.visibility = View.GONE
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {

            }
        }

        ftIncomeTv.setOnClickListener {
            isIncome = true
            categoryAdapter.clear()
            incomes?.let { it1 -> categoryAdapter.addAll(it1) }
            categoryAdapter.notifyDataSetChanged()
            ftIncomeTv.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_ft_income_pressed)
            ftExpenseTv.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_ft_expense_disabled)
        }
        ftExpenseTv.setOnClickListener {
            isIncome = false
            categoryAdapter.clear()
            expenses?.let { it1 -> categoryAdapter.addAll(it1) }
            categoryAdapter.notifyDataSetChanged()
            ftIncomeTv.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_ft_income_disabled)
            ftExpenseTv.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_ft_expense_pressed)
        }
        ftIncomeTv.callOnClick()
        ftCancelTv.setOnClickListener {
            showToast(R.string.ft_trnsaction_canceled)
        }
        ftDoneTv.setOnClickListener {
            val amount = ftAmountEt.text.toString()
            if (amount.toIntOrNull() != null &&
                transaction.accountName != null &&
                transaction.transactionName != null ) {
                transaction.id = System.currentTimeMillis()
                viewModel.saveTransaction(transaction)
                showToast(R.string.ft_transaction_saved)
            } else {
                showToast(R.string.ft_data_error)
            }
        }
    }

    private fun showToast(messageId: Int) {
        Toast.makeText(
            requireContext(),
            getString(messageId),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun initObservers() {
        viewModel.accountsLd.observe(viewLifecycleOwner, Observer {
            accounts = it
            accountAdapter.clear()
            accountAdapter.addAll(it)
        })
        viewModel.expensesLd.observe(viewLifecycleOwner, Observer {
            expenses = it
            categoryAdapter.clear()
            categoryAdapter.addAll(it)
        })
        viewModel.incomesLd.observe(viewLifecycleOwner, Observer {
            incomes = it
        })

    }
}