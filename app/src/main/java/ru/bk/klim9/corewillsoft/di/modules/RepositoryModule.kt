package ru.bk.klim9.corewillsoft.di.modules

import dagger.Module
import dagger.Provides
import ru.bk.klim9.corewillsoft.database.TransactionDao
import ru.bk.klim9.corewillsoft.repositoty.DataRepository
import ru.bk.klim9.movies.database.DatabaseHolder
import javax.inject.Singleton

@Suppress("unused")
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSiteSpecsRepository(transactionDao: TransactionDao) =
        DataRepository(transactionDao)

    @Singleton
    @Provides
    fun provideTransactionDao(): TransactionDao {
        return DatabaseHolder.database().moviesDao()
    }
}