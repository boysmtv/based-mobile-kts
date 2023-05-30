package com.based.kotlin.core.util

import com.based.kotlin.utilities.constants.RespondConstants

val httpError = listOf(
    RespondConstants.HttpCode.CODE_500,
    RespondConstants.HttpCode.CODE_503,
    RespondConstants.HttpCode.CODE_504,
    RespondConstants.HttpCode.CODE_401
)

var listAvoidGeneralError = listOf(
    RespondConstants.HttpCode.CODE_500,
    RespondConstants.HttpCode.CODE_503,
    RespondConstants.HttpCode.CODE_409,
    RespondConstants.HttpCode.CODE_504,
    RespondConstants.HttpCode.CODE_404
)