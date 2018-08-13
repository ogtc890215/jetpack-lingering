package gq.jetpack.lingering.repository

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import gq.jetpack.lingering.AppExecutors
import gq.jetpack.lingering.R
import gq.jetpack.lingering.data.Resource
import gq.jetpack.lingering.data.User
import gq.jetpack.lingering.db.UserDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Singleton
class UserRepository @Inject constructor(
        private val userDao: UserDao,
        private val appExecutors: AppExecutors,
        private val context: Application
) {

    fun loadUser(login: String): LiveData<Resource<User>> {
        return Transformations.map(userDao.findByLogin(login)) { user ->
            user?.let { Resource.success(user) } ?: Resource.error("User $login does not login before.", User(login))
        }
    }

    fun loadAvatars(user: User): LiveData<Resource<Bitmap>> {
        val size = context.resources.getDimensionPixelSize(R.dimen.navigation_avatars_size)
        return MutableLiveData<Resource<Bitmap>>().apply {
            value = Resource.loading(null)
            Glide.with(context).asBitmap().load(user.avatars).into(object : SimpleTarget<Bitmap>(size, size) {
                override fun onLoadFailed(errorDrawable: Drawable?) {
                    value = Resource.error("Failed to load image ${user.avatars}", null)
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    value = Resource.success(resource)
                }
            })
        }
    }

    fun addUser(newUser: User): LiveData<Resource<User>> {
        return MutableLiveData<Resource<User>>().apply {
            appExecutors.diskIO.execute {
                userDao.insert(newUser)
                postValue(Resource.success(newUser))
            }
        }
    }
}