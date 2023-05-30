package com.based.kotlin.feature.dashboard.presentation.viewmodel

import com.based.kotlin.api.splash.domain.load.LoadAppVersionUseCase
import com.based.kotlin.api.splash.domain.post.PostCheckConfigUseCase
import com.based.kotlin.api.splash.domain.remove.RemoveContextCacheUseCase
import com.based.kotlin.api.splash.domain.save.SaveAppVersionUseCase
import com.based.kotlin.api.splash.domain.save.SaveIpAppUseCase
import com.based.kotlin.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val postCheckConfigUseCase: PostCheckConfigUseCase,
    private val saveIPAppUseCase: SaveIpAppUseCase,
    private val removeContextCacheUseCase : RemoveContextCacheUseCase,
    private val saveAppVersionUseCase: SaveAppVersionUseCase,
    private val loadAppVersionUseCase: LoadAppVersionUseCase
) : BaseViewModel() {


}