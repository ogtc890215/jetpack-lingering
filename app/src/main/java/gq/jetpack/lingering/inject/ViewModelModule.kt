package gq.jetpack.lingering.inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import gq.jetpack.lingering.viewmodel.*

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(AudioLibraryViewModel::class)
//    abstract fun bindAudioViewModel(libraryViewModel: AudioLibraryViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(PlaylistsViewModel::class)
//    abstract fun bindPlaylistsViewModel(playlistsViewModel: PlaylistsViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(FolderViewModel::class)
//    abstract fun bindFolderViewModel(folderViewModel: FolderViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(PlayingQueueViewModel::class)
//    abstract fun bindPlayingQueueViewModel(playingQueueViewModel: PlayingQueueViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: InjectViewModelFactory): ViewModelProvider.Factory
}