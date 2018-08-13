package gq.jetpack.lingering.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import gq.jetpack.lingering.MainActivity
import gq.jetpack.lingering.R
import gq.jetpack.lingering.databinding.FragmentLibraryBinding
import gq.jetpack.lingering.inject.Injectable
import javax.inject.Inject

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class MainFragment : Fragment(), Injectable {
    @Inject
    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentLibraryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_library, container, false)
        binding.setLifecycleOwner(this)
//        binding.tabs.setupWithViewPager(binding.libraryPager, true)
//        binding.libraryPager.adapter = LibraryPagerAdapter(fragmentManager!!)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        mainActivity.setupActionBar(binding.toolbar)
    }

//    class LibraryPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
//        @Inject
//        lateinit var context: Context
//
//        override fun getCount() = context.resources.getStringArray(R.array.library_class).size
//
//        override fun getItem(position: Int): Fragment {
//        }
//
//    }
}