package gq.jetpack.lingering.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import gq.jetpack.lingering.AppExecutors
import gq.jetpack.lingering.R
import gq.jetpack.lingering.data.Audio
import gq.jetpack.lingering.databinding.LibraryTrackItemBinding
import gq.jetpack.lingering.ui.common.DataBoundListAdapter

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class TrackLibraryAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val callback: ((Audio) -> Unit)?
) : DataBoundListAdapter<Audio, LibraryTrackItemBinding>(
        appExecutors,
        object : DiffUtil.ItemCallback<Audio>() {
            override fun areItemsTheSame(oldItem: Audio, newItem: Audio): Boolean {
                return oldItem.contentId == newItem.contentId
            }

            override fun areContentsTheSame(oldItem: Audio, newItem: Audio): Boolean {
                return oldItem.title == newItem.title
                        && oldItem.artist == newItem.artist
                        && oldItem.duration == newItem.duration
            }
        }

) {
    override fun createBinding(parent: ViewGroup): LibraryTrackItemBinding {
        val binding = DataBindingUtil.inflate<LibraryTrackItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.library_track_item,
                parent,
                false,
                dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.audio?.let {

            }
        }
        binding.more.setOnClickListener {

        }
        return binding
    }

    override fun bind(binding: LibraryTrackItemBinding, item: Audio) {
        binding.audio = item
    }

}