package gq.jetpack.lingering.inject

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import gq.jetpack.lingering.LingeringApp
import javax.inject.Singleton

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    MainActivityModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(lingeringApp: LingeringApp)
}