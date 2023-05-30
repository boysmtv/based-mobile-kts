package com.based.kotlin.core.ui.base

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.based.kotlin.utilities.constants.Constants.ZERO

/**
 * Storing the state of a custom view during configuration change.
 * While assigning a unique ID to each view component will eventually enable a mechanism of storing
 * the view state effortlessly, most reusable compound custom view will benefit from an enhanced storing mechanism
 * that involves storing both of its own state and its child views' state thoroughly.
 *
 * Feel free to update this class if necessary.
 */
open class BaseViewSavedState @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = ZERO
) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    /**
     * Instead of using the "freezing" mechanism used by this view group's implementation,
     * freeze only the state of itself by calling the "super" version of this view group.
     */
    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>?) {
        dispatchFreezeSelfOnly(container)
    }

    /**
     * The same usage of {@link #dispatchSaveInstanceState(android.util.SparseArray)} only for "thawing" the state
     */
    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>?) {
        dispatchThawSelfOnly(container)
    }

    override fun onSaveInstanceState(): Parcelable? = SavedState(super.onSaveInstanceState()).apply {
        childrenStates = saveChildrenState()
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            state.childrenStates?.run { restoreChildrenState(this) }
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    private fun saveChildrenState(): SparseArray<Parcelable> {
        val childrenStates = SparseArray<Parcelable>()
        children.forEach { child -> child.saveHierarchyState(childrenStates) }

        return childrenStates
    }

    private fun restoreChildrenState(childrenStates: SparseArray<Parcelable>) {
        children.forEach { child -> child.restoreHierarchyState(childrenStates) }
    }
}