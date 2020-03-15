package ru.bk.klim9.corewillsoft.ui.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.bk.klim9.corewillsoft.repositoty.DataRepository
import javax.inject.Inject

/**
 * @author ivan.a.klymenko@gmail.com on 2020-03-14
 */
open class BaseViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var repository: DataRepository
    protected open val cd = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        cd.clear()
    }
}