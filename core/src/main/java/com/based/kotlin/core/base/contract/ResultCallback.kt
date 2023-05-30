package com.mandiri.bds.core.base.contract

interface ResultCallback<T> {
    fun result(result: T) = Unit
    fun close() = Unit
}