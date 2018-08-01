package gq.jetpack.lingering.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import gq.jetpack.lingering.ui.MainFragment

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Module
abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}