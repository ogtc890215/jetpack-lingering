package gq.jetpack.lingering.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import javax.inject.Inject

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class FragmentBindingAdapters @Inject constructor(val fragment: Fragment) {

    @BindingAdapter("image_url")
    fun bindImage(imageView: ImageView, url: String?) {
        Glide.with(fragment).load(url).into(imageView)
    }
}