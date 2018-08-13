package gq.jetpack.lingering.utils

import android.util.Log
import gq.jetpack.lingering.BuildConfig

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
const val APP_LOG_TAG = "Lingering"

fun debug(message: String, tag: String = APP_LOG_TAG) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, message)
    }
}