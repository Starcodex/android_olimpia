package com.starcodex.olimpia.util.arch

open class MutableProcessEventLiveData<T> : ProcessEventLiveData<T>() {

    public override fun setLoading(msgResid: Int) {
        super.setLoading(msgResid)
    }

    public override fun setSuccess(data: T?) {
        super.setSuccess(data)
    }

    public override fun setError(msgResid: Int) {
        super.setError(msgResid)
    }
}