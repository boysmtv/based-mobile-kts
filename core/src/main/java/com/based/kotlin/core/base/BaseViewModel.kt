package com.based.kotlin.core.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private val validationMap = hashMapOf<Int, Boolean>()

    private val _dynamicFieldValidator = MutableLiveData<HashMap<Int, Boolean>>()
    val dynamicFieldValidator: LiveData<HashMap<Int, Boolean>> = _dynamicFieldValidator

    fun setDynamicFieldValidation(key: Int, isActive: Boolean) {
        _dynamicFieldValidator.value?.clear()
        validationMap[key] = isActive
        _dynamicFieldValidator.postValue(validationMap)
    }

    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        val suppressedException = exception.suppressed

        if (suppressedException.isNotEmpty()) {
            Timber.d(exception, "Caught $exception with another suppresed exception(s):")

            suppressedException.forEach { throwable ->
                Timber.d("Also Caught $throwable")
            }
        } else {
            Timber.d("Caught $exception")
        }
    }

    protected inline fun launchWithViewModel(
        crossinline launchBlock: suspend CoroutineScope.() -> Unit,
        noinline errorHandler: (Throwable) -> Unit
    ): Job {
        val exceptionHandler = CoroutineExceptionHandler { _, exception -> errorHandler(exception) }

        return viewModelScope.launch(exceptionHandler) { launchBlock() }
    }

    protected inline fun launchWithViewModel(crossinline launchBlock: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch(exceptionHandler) { launchBlock() }

    @OptIn(InternalCoroutinesApi::class)
    fun <A> collectFlow(flow: Flow<A>, data: MutableLiveData<A>, isMainThread: Boolean = false) = with(viewModelScope) {
        val collector = object : FlowCollector<A> {
            override suspend fun emit(value: A) {
                if (isMainThread) data.value = value else data.postValue(value)
            }
        }
        launch { flow.collect(collector) }
    }
}
