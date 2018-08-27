package gq.jetpack.lingering.utils

import android.text.format.DateFormat

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
object Utilities {
    @JvmStatic
    fun formatAudioDuration(duration: Long): CharSequence = DateFormat.format("mm:ss", duration)
}