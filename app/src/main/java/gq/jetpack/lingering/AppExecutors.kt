package gq.jetpack.lingering

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Singleton
class AppExecutors(
        val diskIO: Executor,
        val networkIO: Executor,
        val mainThread: Executor
) {
    @Inject
    constructor() : this(
            Executors.newSingleThreadExecutor(),
            Executors.newFixedThreadPool(3),
            MainThreadExecutor()
    )

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}