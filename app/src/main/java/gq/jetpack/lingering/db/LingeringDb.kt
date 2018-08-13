package gq.jetpack.lingering.db

import androidx.room.Database
import androidx.room.RoomDatabase
import gq.jetpack.lingering.data.User

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Database(
        entities = [User::class],
        version = 1,
        exportSchema = true
)
abstract class LingeringDb : RoomDatabase() {

    abstract fun userDao(): UserDao
}