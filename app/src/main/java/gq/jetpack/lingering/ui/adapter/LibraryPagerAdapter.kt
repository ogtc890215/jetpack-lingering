package gq.jetpack.lingering.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import gq.jetpack.lingering.AppExecutors
import gq.jetpack.lingering.R

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class LibraryPagerAdapter(
        private val context: Context,
        private val appExecutors: AppExecutors,
        private val dataBindingComponent: DataBindingComponent
) : PagerAdapter() {

    override fun getCount(): Int = Library.values().size

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return (obj as Page).view == view
    }

    override fun getItemPosition(obj: Any): Int {
        return Library.values().indexOf((obj as Page).library)
    }

    override fun getPageTitle(position: Int): CharSequence? =
            context.getString(Library.values()[position].stringId)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val library = Library.values()[position]
        val context = container.context
        val recyclerView = RecyclerView(context)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        recyclerView.adapter = library.generateLibraryAdapter(dataBindingComponent, appExecutors)
        container.addView(recyclerView)

        return Page(library, recyclerView)
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView((obj as Page).view)
    }

    class Page(val library: Library, val view: RecyclerView)

    enum class Library(val stringId: Int) {
        TRACK(R.string.library_track),
        ALBUM(R.string.library_album),
        ARTIST(R.string.library_artist),
        GENRE(R.string.library_genre);

        fun generateLibraryAdapter(
                dataBindingComponent: DataBindingComponent,
                appExecutors: AppExecutors
        ): RecyclerView.Adapter<out RecyclerView.ViewHolder> {
            return when (this) {
                TRACK -> TrackLibraryAdapter(dataBindingComponent, appExecutors, null)
                else -> throw IllegalArgumentException("Undefined library page adapter for $name")
            }
        }
    }
}