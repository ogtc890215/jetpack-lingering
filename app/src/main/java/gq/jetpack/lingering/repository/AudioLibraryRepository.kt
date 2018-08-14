package gq.jetpack.lingering.repository

import android.app.Application
import android.content.ContentProviderClient
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import gq.jetpack.lingering.AppExecutors
import gq.jetpack.lingering.data.Audio
import gq.jetpack.lingering.data.Resource
import gq.jetpack.lingering.utils.MediaStoreResource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Singleton
class AudioLibraryRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val context: Application
) {

    companion object {
        val TRACKS_PROJECTION = arrayOf(
                MediaStore.Audio.AudioColumns._ID,
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.AudioColumns.ALBUM_ID,
                MediaStore.Audio.AudioColumns.ARTIST,
                MediaStore.Audio.AudioColumns.ARTIST_ID,
                MediaStore.Audio.AudioColumns.DURATION,
                MediaStore.Audio.AudioColumns.TRACK
        )
        const val TRACKS_SELECTION = "${MediaStore.Audio.AudioColumns.IS_MUSIC} = 1 AND ${MediaStore.Audio.AudioColumns.TITLE} != ''"
    }

    fun loadTracks(): LiveData<Resource<List<Audio>>> {
        return object : MediaStoreResource<Audio>(appExecutors, context) {

            override fun getContentProviderUri(): Uri {
                return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }

            override fun createQueryCursor(client: ContentProviderClient): Cursor? {
                return client.query(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        TRACKS_PROJECTION,
                        TRACKS_SELECTION,
                        null,
                        MediaStore.Audio.Media.DEFAULT_SORT_ORDER
                )
            }

            override fun getItemFromCursor(cursor: Cursor): Audio {
                return Audio(
                        contentId = cursor.getLong(0),
                        title = cursor.getString(1),
                        album = cursor.getString(2),
                        albumId = cursor.getLong(3),
                        artist = cursor.getString(4),
                        artistId = cursor.getLong(5),
                        duration = cursor.getLong(6),
                        track = cursor.getInt(7)
                )
            }

        }.asLiveData()
    }
}