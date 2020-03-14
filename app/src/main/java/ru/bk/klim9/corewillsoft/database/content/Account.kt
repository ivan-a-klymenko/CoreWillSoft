package ru.bk.klim9.corewillsoft.database.content

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey
    val accountName: String
) {
    var amount: Int = 0
}