package com.based.kotlin.kts

import com.based.kotlin.kts.util.Constants.NATIVE_LIB

class Native {
    companion object {
        init {
            System.loadLibrary(NATIVE_LIB)
        }
        external fun isMagiskPresentNative(): Boolean
    }
}