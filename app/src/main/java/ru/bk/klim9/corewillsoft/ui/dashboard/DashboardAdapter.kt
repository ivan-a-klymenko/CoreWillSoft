package ru.bk.klim9.corewillsoft.ui.dashboard

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_account.view.*
import kotlinx.android.synthetic.main.item_transaction.view.*
import ru.bk.klim9.corewillsoft.R
import ru.bk.klim9.corewillsoft.database.content.Account
import ru.bk.klim9.corewillsoft.database.content.INCOME
import ru.bk.klim9.corewillsoft.database.content.Transaction
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
const val TRANSACTION = 1
const val ACCOUNT = 2

class DashboardAdapter internal constructor(private val action: Action) :
    RecyclerView.Adapter<DashboardAdapter.Holder>() {

    private val items = ArrayList<Item>()
    private var currency: Currency? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = when(viewType) {
            TRANSACTION -> LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false) as TransactionItemView
            else -> LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false) as AccountItemView
        }
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (currency == null) {
            val defaultLocale = Locale.getDefault()
            currency = Currency.getInstance(defaultLocale)
        }
        val item = items[position]
        val itemView = holder.view
        when (item.type) {
            TRANSACTION -> {
                item.transaction?.let { (itemView as TransactionItemView).bind(it, currency!!) }
            }
            else -> {
                item.account?.let { (itemView as AccountItemView).bind(it) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int = items[position].type

    override fun getItemCount(): Int = items.size

    fun setData(newList: List<Item>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    inner class Holder(val view: View) : RecyclerView.ViewHolder(view)

    data class Item(val type: Int, val transaction: Transaction?, val account: Account?)

    interface Action {

    }
}

class AccountItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun bind(account: Account) {
        iaTitleTv.text = account.accountName
        iaAmountTv.text = account.amount.toString()
    }
}

class TransactionItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun bind(transaction: Transaction, currency: Currency) {
        itCategoryTv.text = transaction.transactionName
        itDateTv.text = transaction.toDate()
        when (transaction.transactionType) {
            INCOME -> {
                val a = "${currency?.symbol}${transaction.amount}"
                itAmountTv.text = a
                setStyle(R.style.AccountIncome,itAmountTv)
            }
            else -> {
                val s = "-${currency?.symbol}${transaction.amount}"
                itAmountTv.text = s
                setStyle(R.style.AccountExpenses,itAmountTv)
            }
        }
    }

    private fun setStyle(styleId: Int, textView: TextView) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            textView.setTextAppearance(context, styleId)
        } else {
            textView.setTextAppearance(styleId)
        }
    }
}