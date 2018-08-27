package gq.jetpack.lingering.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import gq.jetpack.lingering.ui.MainActivity
import gq.jetpack.lingering.service.MediaPlaybackService

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Module
abstract class ComponentsModule {

    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contribulteMediaPlaybackService(): MediaPlaybackService
}