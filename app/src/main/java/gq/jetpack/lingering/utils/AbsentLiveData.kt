package gq.jetpack.lingering.utils

import androidx.lifecycle.LiveData

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}