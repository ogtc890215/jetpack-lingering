package gq.jetpack.lingering.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import gq.jetpack.lingering.AppExecutors
import gq.jetpack.lingering.R
import gq.jetpack.lingering.databinding.FragmentDataBindingComponent
import gq.jetpack.lingering.databinding.FragmentLibraryBinding
import gq.jetpack.lingering.inject.Injectable
import gq.jetpack.lingering.ui.adapter.LibraryPagerAdapter
import gq.jetpack.lingering.viewmodel.AudioLibraryViewModel
import javax.inject.Inject

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class MainFragment : Fragment(), Injectable {
    @Inject
    lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentLibraryBinding
    private val dataBindingComponent = FragmentDataBindingComponent(this)
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var libraryViewModel: AudioLibraryViewModel
    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        libraryViewModel = ViewModelProviders.of(this, viewModelFactory).get(AudioLibraryViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_library, container, false)
        binding.setLifecycleOwner(this)
        binding.tabs.setupWithViewPager(binding.libraryPager, true)
        binding.libraryPager.adapter =
                LibraryPagerAdapter(context!!, appExecutors, dataBindingComponent)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        mainActivity.setupActionBar(binding.toolbar)
    }
}