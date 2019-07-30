package fi.tomy.salminen.doublehelix.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import fi.tomy.salminen.doublehelix.R
import kotlin.math.max


class FullScreenScrollBehaviour(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    companion object {
        val NO_ID = -1
    }

    val anchorId: Int
    var consumalbeOffset: Int? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.View)
        anchorId = typedArray.getResourceId(R.styleable.View_contentAnchor, NO_ID)
        typedArray.recycle()
    }

    private fun getAnchor(parent: CoordinatorLayout): View? {
        return if (anchorId != NO_ID) parent.findViewById(anchorId) else null
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return anchorId != NO_ID && dependency.id == anchorId
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val anchor = getAnchor(parent) ?: return false
        // Initial location
        consumalbeOffset = anchor.bottom - child.top
        return true
    }

    override fun onLayoutChild(parent: CoordinatorLayout, child: View, layoutDirection: Int): Boolean {
        val anchor = getAnchor(parent) ?: return false
        parent.onLayoutChild(child, layoutDirection)
        if (consumalbeOffset != null) {
            ViewCompat.offsetTopAndBottom(child, consumalbeOffset!!)
            return true
        }

        return false
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        val anchor = getAnchor(coordinatorLayout)
        return anchor != null && axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    /*
    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        val anchor = getAnchor(coordinatorLayout) ?: return
        val offset: Int

        if (target.scrollY > anchor.bottom - child.top) {
            offset = anchor.bottom - child.top
            return super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed)
        }

        val maxOffset: Int

        if (dyConsumed > 0) {
            maxOffset = anchor.top - child.top
            offset = Math.max(maxOffset, -dyConsumed)

        } else {
            maxOffset = anchor.bottom - child.top
            offset = Math.min(maxOffset, -dyConsumed)
        }

        if (offset == 0) {
            super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed)
        } else {
            consumed[1] = offset
            consumalbeOffset = offset
            ViewCompat.offsetTopAndBottom(child, offset)
        }
    }
    */
}