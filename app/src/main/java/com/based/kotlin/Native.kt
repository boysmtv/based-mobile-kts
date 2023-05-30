package com.based.kotlin

import com.based.kotlin.util.Constants.NATIVE_LIB

class Native {
    companion object {
        init {
            System.loadLibrary(NATIVE_LIB)
        }

        external fun isMagiskPresentNative(): Boolean
    }
}