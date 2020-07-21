package com.starcodex.olimpia.util.arch

import androidx.lifecycle.Observer

open class EventObserver<T>(private val onEventChanged: (T) -> Unit) : Observer<Event<T>> {

    override fun onChanged(event: Event<T>?) {
        event?.consume()?.let { onEventChanged(it) }
    }
}