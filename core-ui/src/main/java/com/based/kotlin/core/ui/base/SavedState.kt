package com.based.kotlin.core.ui.base

import android.os.Parcel
import android.os.Parcelable
import android.util.SparseArray
import android.view.View

internal class SavedState : View.BaseSavedState {

    internal var childrenStates: SparseArray<Parcelable>? = null

    constructor(superState: Parcelable?) : super(superState)

    constructor(childrenState: Parcel) : super(childrenState) {
        childrenStates = childrenState.readSparseArray(javaClass.classLoader)
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSparseArray(childrenStates)
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<SavedState> {
            override fun createFromParcel(childrenState: Parcel): SavedState = SavedState(childrenState)

            override fun newArray(arraySize: Int): Array<SavedState?> = arrayOfNulls(arraySize)
        }
    }
}