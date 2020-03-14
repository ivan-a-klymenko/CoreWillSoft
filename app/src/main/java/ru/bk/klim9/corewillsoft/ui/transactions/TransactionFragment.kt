package ru.bk.klim9.corewillsoft.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_transaction.*
import ru.bk.klim9.corewillsoft.R
import javax.inject.Inject

class TransactionFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: TransactionViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TransactionViewModel::class.java)
    }
    private var incomes: List<String>? = null
    private var expenses: List<String>? = null
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
        ftAccountSp.prompt = getString(R.string.select_account)
        ftAccountSp.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }

        }

        ftCategorySp.adapter = categoryAdapter
        ftCategorySp.prompt = getString(R.string.select_account)
        ftCategorySp.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }

        }

        ftIncomeTv.setOnClickListener {
            categoryAdapter.clear()
            incomes?.let { it1 -> categoryAdapter.addAll(it1) }
        }
        ftExpenseTv.setOnClickListener {
            categoryAdapter.clear()
            expenses?.let { it1 -> categoryAdapter.addAll(it1) }
        }
        ftIncomeTv.callOnClick()
    }

    private fun initObservers() {
        viewModel.accountsLd.observe(viewLifecycleOwner, Observer {
            accountAdapter.clear()
            accountAdapter.addAll(it)
        })
        viewModel.expensesLd.observe(viewLifecycleOwner, Observer {
            expenses = it
        })
        viewModel.incomesLd.observe(viewLifecycleOwner, Observer {
            incomes = it
        })

    }
}