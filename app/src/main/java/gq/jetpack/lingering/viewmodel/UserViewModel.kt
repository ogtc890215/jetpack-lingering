package gq.jetpack.lingering.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import gq.jetpack.lingering.LingeringApp
import gq.jetpack.lingering.data.Resource
import gq.jetpack.lingering.data.Status
import gq.jetpack.lingering.data.User
import gq.jetpack.lingering.repository.UserRepository
import gq.jetpack.lingering.utils.AbsentLiveData
import gq.jetpack.lingering.utils.VetoNoChangeLiveData
import javax.inject.Inject

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    companion object {
        private const val DEFAULT_LOGIN = "default"
    }

    private val login = VetoNoChangeLiveData<String>()

    private val user: LiveData<Resource<User>> = Transformations.switchMap(login) { _login ->
        _login?.let { userRepository.loadUser(it) } ?: AbsentLiveData.create()
    }

    val avatars: LiveData<Bitmap?> = Transformations.switchMap(user) { userRes ->
        userRes?.takeIf { it.status == Status.SUCCESS }?.data?.let { user ->
            Transformations.map(userRepository.loadAvatars(user)) { bmpRes -> bmpRes.data }
        } ?: AbsentLiveData.create()
    }

    fun defaultLogin(owner: LifecycleOwner) {
        user.observe(owner, object : Observer<Resource<User>> {
            private var retryCount = 1
            override fun onChanged(t: Resource<User>?) {
                when (t?.status) {
                    Status.ERROR -> {
                        Log.w(LingeringApp.TAG, "${t.data?.login} no login, try to login default user")
                        if (retryCount-- > 0) {
                            userRepository.addUser(User(
                                    DEFAULT_LOGIN,
                                    Uri.parse("file:///android_asset/default_avatars.jpg"))
                            ).observe(owner, Observer { login.retry() })
                        } else {
                            user.removeObserver(this)
                        }
                    }
                    Status.SUCCESS -> {
                        Log.d(LingeringApp.TAG, "${t.data} login.")
                        user.removeObserver(this)
                    }
                    Status.LOADING -> Log.d(LingeringApp.TAG, "Pending login")
                }
            }
        })
        login.setValue(DEFAULT_LOGIN)
    }

}