package gq.jetpack.lingering.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.android.AndroidInjection

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class MediaPlaybackService : Service() {

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}