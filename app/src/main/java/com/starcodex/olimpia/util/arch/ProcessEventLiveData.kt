package com.starcodex.olimpia.util.arch

import androidx.annotation.StringRes


open class ProcessEventLiveData<T> : EventLiveData<Resource<T>>() {

    protected open fun setLoading(@StringRes msgResid: Int = -1) {
        setData(Resource.loading(msgResid))
    }

    protected open fun setSuccess(data: T?) {
        setData(Resource.success(data))
    }

    protected open fun setError(@StringRes msgResid: Int = -1) {
        setData(Resource.error(msgResid))
    }
}