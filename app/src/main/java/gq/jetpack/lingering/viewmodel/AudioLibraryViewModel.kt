package gq.jetpack.lingering.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import gq.jetpack.lingering.data.Audio
import gq.jetpack.lingering.data.Resource
import gq.jetpack.lingering.repository.AudioLibraryRepository
import gq.jetpack.lingering.utils.TriggerLiveData
import javax.inject.Inject

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class AudioLibraryViewModel @Inject constructor(libraryRepository: AudioLibraryRepository) : ViewModel() {

    private val refresh = TriggerLiveData()

    val tracks: LiveData<Resource<List<Audio>>> = Transformations.switchMap(refresh) { libraryRepository.loadTracks() }

}