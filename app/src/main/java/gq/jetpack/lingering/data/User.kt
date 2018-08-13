package gq.jetpack.lingering.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.TypeConverters
import gq.jetpack.lingering.db.DbTypeConverters

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Entity(primaryKeys = ["login"])
@TypeConverters(DbTypeConverters::class)
data class User(
        val login: String,
        val avatars: Uri?
) {
    @Ignore
    constructor(login: String) : this(login, null)
}