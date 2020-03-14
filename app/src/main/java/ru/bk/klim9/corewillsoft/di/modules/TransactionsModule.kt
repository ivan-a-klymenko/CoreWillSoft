package ru.bk.klim9.corewillsoft.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ru.bk.klim9.corewillsoft.ui.transactions.TransactionFragment
import ru.bk.klim9.corewillsoft.ui.transactions.TransactionViewModel

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
@Suppress("unused")
@Module
abstract class TransactionsModule {
    @ContributesAndroidInjector
    abstract fun contributesTransactionsFragment(): TransactionFragment

    @Binds
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    abstract fun bindsTransactionsViewModel(transactionViewModel: TransactionViewModel): ViewModel
}