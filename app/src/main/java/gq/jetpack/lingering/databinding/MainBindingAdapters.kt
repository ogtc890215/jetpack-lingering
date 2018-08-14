package gq.jetpack.lingering.databinding

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.databinding.BindingAdapter
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import gq.jetpack.lingering.R

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
object MainBindingAdapters {

    @JvmStatic
    @BindingAdapter("avatars", "avatars_placeholder", requireAll = false)
    fun setAvatar(nav: NavigationView, avatars: Bitmap?, placeholder: Drawable?) {
        nav.getHeaderView(0)?.findViewById<ImageView>(R.id.avatar)?.setImageDrawable(
                avatars?.let {
                    RoundedBitmapDrawableFactory.create(nav.resources, it).apply { isCircular = true }
                } ?: placeholder)
    }

    @JvmStatic
    @BindingAdapter("tabs")
    fun setTabs(tabLayout: TabLayout, tabs: Array<String>) {
        tabs.forEach { tab ->
            tabLayout.addTab(tabLayout.newTab().apply { text = tab })
        }
    }
}