package gq.jetpack.lingering.db

import android.net.Uri
import androidx.room.TypeConverter

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
object DbTypeConverters {

    @JvmStatic
    @TypeConverter
    fun uriToString(uri: Uri): String = uri.toString()

    @JvmStatic
    @TypeConverter
    fun stringToUri(string: String): Uri = Uri.parse(string)

}