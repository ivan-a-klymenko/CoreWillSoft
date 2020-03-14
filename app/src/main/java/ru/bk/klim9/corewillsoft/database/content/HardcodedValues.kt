package ru.bk.klim9.corewillsoft.database.content

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
enum class Accounts (val account: String) {
    CASH("Cash"),
    CREDIT_CARD("Credit Card"),
    BANK_ACCOUNT("Bank account")
}

enum class Incomes (val income: String) {
    TAX("Tax"),
    GROCERY("Grocery"),
    ENTERTAINMENT("Entertainment"),
    GYM("Gym"),
    HEALTH("Health")
}

enum class Expenses (val expense: String) {
    SALARY("Salary"),
    DIVIDENDS("Dividends")
}
