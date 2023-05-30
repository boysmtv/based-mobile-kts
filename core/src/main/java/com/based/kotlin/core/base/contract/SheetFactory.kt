package com.based.kotlin.core.base.contract

interface SheetFactory<Input, Result> : SheetFragment<Result> {
    var model: Input?
    fun setInput(input: Input)
}