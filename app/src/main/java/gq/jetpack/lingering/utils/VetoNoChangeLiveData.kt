package gq.jetpack.lingering.utils

import androidx.lifecycle.MutableLiveData

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
open class VetoNoChangeLiveData<T> : MutableLiveData<T>() {

    override fun postValue(value: T) {
        if (value == getValue()) {
            return
        }
        super.postValue(value)
    }

    override fun setValue(value: T) {
        if (value == getValue()) {
            return
        }
        super.setValue(value)
    }

    fun retry() {
        value?.let { super.setValue(it) }
    }
}

enum class Trigger {
    TOGGLE
}

class TriggerLiveData : VetoNoChangeLiveData<Trigger>() {

    init {
        setValue(Trigger.TOGGLE)
    }

}