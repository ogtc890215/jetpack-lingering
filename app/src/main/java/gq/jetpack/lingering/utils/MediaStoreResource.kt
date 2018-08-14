package gq.jetpack.lingering.utils

import android.content.ContentProviderClient
import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gq.jetpack.lingering.AppExecutors
import gq.jetpack.lingering.data.Resource

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
abstract class MediaStoreResource<ResultType>
@MainThread constructor(appExecutors: AppExecutors, context: Context) {
    private val result = MutableLiveData<Resource<List<ResultType>>>()

    init {
        result.value = Resource.loading(null)
        appExecutors.diskIO.execute {
            val uri = getContentProviderUri()
            context.contentResolver.acquireUnstableContentProviderClient(uri).use { client ->
                if (client == null)
                    result.value = Resource.error("Cannot access content provider: $uri", null)
                else
                    try {
                        val cursor = createQueryCursor(client)
                        val list = ArrayList<ResultType>()
                        if (cursor != null && cursor.moveToFirst()) {
                            do {
                                list.add(getItemFromCursor(cursor))
                            } while (cursor.moveToNext())
                        }
                        result.value = Resource.success(list)
                    } catch (e: Exception) {
                        result.value = Resource.error("Failed when loading content provider $uri", null, e)
                    }
            }
        }
    }

    abstract fun getContentProviderUri(): Uri

    abstract fun createQueryCursor(client: ContentProviderClient): Cursor?

    abstract fun getItemFromCursor(cursor: Cursor): ResultType

    fun asLiveData() = result as LiveData<Resource<List<ResultType>>>

}