package gq.jetpack.lingering.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import gq.jetpack.lingering.MainActivity

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}