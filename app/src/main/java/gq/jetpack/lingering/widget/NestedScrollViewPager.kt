package gq.jetpack.lingering.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.view.*
import androidx.viewpager.widget.ViewPager

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class NestedScrollViewPager @JvmOverloads constructor(context: Context, attr: AttributeSet? = null)
    : ViewPager(context, attr), NestedScrollingChild2, NestedScrollingParent2 {

    private val parentScrollingHelper = NestedScrollingParentHelper(this)
    private val childScrollingHelper = NestedScrollingChildHelper(this)

    // NestedScrollingParent2
    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        dispatchNestedPreScroll(dx, dy, consumed, null, type)
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        parentScrollingHelper.onStopNestedScroll(target, type)
        stopNestedScroll(type)
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int) = axes.and(ViewCompat.SCROLL_AXIS_VERTICAL) != 0

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        parentScrollingHelper.onNestedScrollAccepted(child, target, axes, type)
        startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, type)
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        val oldScrollY = scrollY
        scrollBy(0, dyUnconsumed)
        val myConsumed = scrollY - oldScrollY
        val myUnconsumed = dyUnconsumed - myConsumed
        dispatchNestedScroll(0, dyConsumed, 0, myUnconsumed, null, type)
    }

    // NestedScrollingChild2
    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?, type: Int): Boolean = childScrollingHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type)

    override fun startNestedScroll(axes: Int, type: Int) = childScrollingHelper.startNestedScroll(axes, type)

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?, type: Int) = childScrollingHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)

    override fun stopNestedScroll(type: Int) = childScrollingHelper.stopNestedScroll(type)

    override fun hasNestedScrollingParent(type: Int) = childScrollingHelper.hasNestedScrollingParent(type)

}
