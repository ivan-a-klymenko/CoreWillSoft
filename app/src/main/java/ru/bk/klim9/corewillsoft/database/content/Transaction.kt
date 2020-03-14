package ru.bk.klim9.corewillsoft.database.content

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
const val INCOME = 1
const val EXPENSE = 2

@Entity(tableName = "transactions")
data class Transaction (
    @PrimaryKey
    val id: Long,
    val accountName: String,
    val transactionName: String,
    val transactionType: Int,
    val amount: Int
) {
    fun toDate(): String {
        return "03/07/2019 16:30"
    }
}