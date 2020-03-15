package ru.bk.klim9.corewillsoft.database.content

import android.annotation.SuppressLint
import android.text.format.DateUtils
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
const val INCOME = 1
const val EXPENSE = 2

@Entity(tableName = "transactions")
data class Transaction (
    @PrimaryKey
    var id: Long?,
    var accountName: String?,
    var transactionName: String?,
    var transactionType: Int?,
    var amount: Int?
) {

    constructor() : this(null, null, null, null, null)

    @SuppressLint("SimpleDateFormat")
    fun toDate(): String {
        val date = Date(this.id!!)
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm")
        return format.format(date)
    }
}