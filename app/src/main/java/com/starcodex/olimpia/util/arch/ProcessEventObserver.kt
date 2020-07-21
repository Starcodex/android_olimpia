package com.starcodex.olimpia.util.arch

class ProcessEventObserver<T>(
    private val onLoading: (Int) -> Unit,
    private val onSuccess: (T?) -> Unit,
    private val onError: (Int) -> Unit
) : EventObserver<Resource<T>>({
    when (it.status) {
        Status.LOADING -> onLoading(it.msgResid)
        Status.SUCCESS -> onSuccess(it.data)
        Status.ERROR -> onError(it.msgResid)
    }
})