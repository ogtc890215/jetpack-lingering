package gq.jetpack.lingering

import android.app.Activity
import android.app.Application
import android.app.Service
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import gq.jetpack.lingering.inject.AppInjector
import javax.inject.Inject

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class LingeringApp : Application(), HasActivityInjector, HasServiceInjector {
    companion object {
        const val TAG = "Lingering"
    }

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun serviceInjector(): AndroidInjector<Service> = dispatchingServiceInjector
}