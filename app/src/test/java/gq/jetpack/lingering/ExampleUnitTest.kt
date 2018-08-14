package gq.jetpack.lingering

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import gq.jetpack.lingering.utils.AbsentLiveData
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun autoTriggerLiveData() {
        val trigger = MutableLiveData<Boolean>()
        trigger.value = true
        val result = Transformations.switchMap(trigger) {
            System.out.println("$it")
            AbsentLiveData.create<Boolean>()
        }
    }
}
