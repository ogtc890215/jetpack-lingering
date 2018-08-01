package gq.jetpack.lingering

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import gq.jetpack.lingering.inject.AppInjector
import javax.inject.Inject

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class LingeringApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}