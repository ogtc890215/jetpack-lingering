package gq.jetpack.lingering.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import gq.jetpack.lingering.data.Audio
import gq.jetpack.lingering.data.Resource
import gq.jetpack.lingering.repository.AudioLibraryRepository
import javax.inject.Inject

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class AudioLibraryViewModel @Inject constructor(libraryRepository: AudioLibraryRepository) : ViewModel() {

    val tracks: LiveData<Resource<List<Audio>>> = libraryRepository.loadTracks()
}