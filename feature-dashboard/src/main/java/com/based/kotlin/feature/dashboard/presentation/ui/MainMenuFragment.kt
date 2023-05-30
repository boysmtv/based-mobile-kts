package com.based.kotlin.feature.dashboard.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.based.kotlin.core.base.BaseFragment
import com.based.kotlin.feature.dashboard.databinding.ActivityMainMenuBinding
import com.based.kotlin.feature.dashboard.presentation.viewmodel.MainMenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : BaseFragment<ActivityMainMenuBinding>() {

    private var preparationSuccess: Boolean = false

    private lateinit var changeCardPreparationBundle: Bundle

    private var isCardActive: Boolean = false

    internal val viewModel: MainMenuViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ActivityMainMenuBinding =
        ActivityMainMenuBinding.inflate(inflater, container, false)

    override fun initView() {

    }

    override fun onPause() {
        super.onPause()
        dismissDialogGeneralError()
    }

}