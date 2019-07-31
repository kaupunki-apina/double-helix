package fi.tomy.salminen.doublehelix.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import fi.tomy.salminen.doublehelix.R


class FullScreenScrollBehaviour(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    companion object {
        val NO_ID = -1
    }

    // val STATE_RESTING = 1
    // val STATE_DRAGGING = 2
    // var dragState = STATE_RESTING
    val anchorId: Int
    var consumalbeOffset: Int? = null
    /*
    var viewDragHelper: ViewDragHelper? = null
    val viewDragHelperCallback = object : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun onViewDragStateChanged(state: Int) {
            if (state == ViewDragHelper.STATE_DRAGGING) {
                dragState = STATE_DRAGGING
            }
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            dragState = STATE_RESTING
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        override fun getViewVerticalDragRange(child: View): Int {
            return -200
        }
    }
    */

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
        // Handles scrolling from upper bound of the screen down anchor bottom
        val anchor = getAnchor(coordinatorLayout) ?: return

        if (dyUnconsumed < 0) {
            val maxOffset = anchor.bottom - child.top
            val offset = Math.max(maxOffset, dyUnconsumed)
            consumalbeOffset = offset
            consumed[1] = offset
            ViewCompat.offsetTopAndBottom(child, offset)
        }

        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: View, event: MotionEvent): Boolean {
        if (!child.isShown) {
            return false
        }

        val action = event.actionMasked

        if (action == MotionEvent.ACTION_MOVE) {
            if (viewDragHelper == null) {
                viewDragHelper = ViewDragHelper.create(parent, viewDragHelperCallback)
            }

            viewDragHelper!!.processTouchEvent(event)
            return viewDragHelper!!.touchSlop < event.y
        }

        return false
    }
    */
}