package gq.jetpack.lingering.databinding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class FragmentDataBindingComponent(fragment: Fragment) : DataBindingComponent {
    private val adapters = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters(): FragmentBindingAdapters = adapters
}