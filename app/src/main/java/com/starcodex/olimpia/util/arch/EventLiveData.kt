package com.starcodex.olimpia.util.arch

import androidx.lifecycle.LiveData

open class EventLiveData<T> : LiveData<Event<T>>() {

    protected open fun setData(data: T) {
        value = Event(data)
    }
}