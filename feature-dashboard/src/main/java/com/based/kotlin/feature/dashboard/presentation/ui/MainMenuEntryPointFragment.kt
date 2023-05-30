package com.based.kotlin.feature.dashboard.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.based.kotlin.core.base.BaseFragment
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_MAIN_MENU
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_SCENARIO
import com.based.kotlin.feature.dashboard.databinding.FragmentMainMenuEntryPointBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuEntryPointFragment : BaseFragment<FragmentMainMenuEntryPointBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainMenuEntryPointBinding =
        FragmentMainMenuEntryPointBinding.inflate(inflater, container, false)

    override fun initView() {
        when (arguments?.getString(ARGS_KEY_SCENARIO)) {
            ARGS_KEY_MAIN_MENU ->
                findNavController().navigate(MainMenuEntryPointFragmentDirections.actionToFragmentMainMenuHome())
            else -> Unit
        }
    }
}