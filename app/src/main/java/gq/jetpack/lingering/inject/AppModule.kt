package gq.jetpack.lingering.inject

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import gq.jetpack.lingering.LingeringApp
import gq.jetpack.lingering.db.LingeringDb
import gq.jetpack.lingering.db.UserDao
import javax.inject.Singleton

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun providerLingeringDB(app: Application): LingeringDb {
        return Room
                .databaseBuilder(app, LingeringDb::class.java, "lingering.db")
                .build()
    }

    @Singleton
    @Provides
    fun providerUserDao(db: LingeringDb): UserDao = db.userDao()
}