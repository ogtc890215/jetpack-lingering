package gq.jetpack.lingering.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Entity()
data class Audio(
        @PrimaryKey
        @ColumnInfo(name = "_id")
        val contentId: Long,
        val title: String,
        val album: String?,
        @ColumnInfo(name = "album_id")
        val albumId: Long,
        @ColumnInfo(name = "album_art")
//        val albumArt: String?,
        val artist: String?,
        @ColumnInfo(name = "artist_id")
        val artistId: Long,
//        val genre: String?,
//        @ColumnInfo(name = "genre_id")
//        val genreId: Long,
        val duration: Long,
//        @ColumnInfo(name = "data")
//        val path: String,
//        @ColumnInfo(name = "date_added")
//        val dataAdded: Date,
//        @ColumnInfo(name = "mime_type")
//        val mimeType: String?
        val track: Int
)