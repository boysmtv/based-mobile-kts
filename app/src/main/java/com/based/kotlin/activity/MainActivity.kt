package com.based.kotlin.activity

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.appdynamics.eumagent.runtime.AgentConfiguration
import com.based.kotlin.KtsApplication
import com.based.kotlin.R
import com.based.kotlin.core.base.BaseActivity
import com.based.kotlin.core.entity.dashboard.ReservationState
import com.based.kotlin.core.nav.NavControllerBinder
import com.based.kotlin.core.ui.widget.dialog.ProgressBarDialog
import com.based.kotlin.databinding.ActivityMainBinding
import com.based.kotlin.util.Constants.NATIVE_LIB
import com.based.kotlin.utilities.constants.Constants.DEFAULT_APPLICATION_IDLE_TIME
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING
import com.based.kotlin.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var binder: NavControllerBinder

    @Inject
    lateinit var agentConfiguration: AgentConfiguration
    lateinit var menu: Menu

    internal lateinit var systemDialogsReceiver: BroadcastReceiver

    private lateinit var appBarConfiguration: AppBarConfiguration

    internal lateinit var responseCallback: (Result<ReservationState>) -> Unit

    internal val viewModel: MainActivityViewModel by viewModels()

    internal var applicationIdleTime = DEFAULT_APPLICATION_IDLE_TIME.toLong()

    internal val dialog by lazy { ProgressBarDialog() }

    internal var activeFragment = R.id.fragment_auth

    internal val navController by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()
    }

    override val eventListener by lazy { (application as KtsApplication).eventListener }

    internal var handler: Handler? = Handler(Looper.getMainLooper())

    private var scenarioOnBack: String? = EMPTY_STRING

    internal val nonUserTypeFragments = setOf(
        R.id.fragment_splash,
        R.id.fragment_auth
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        eventListener.bindActivity(this)
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        eventListener.common.setCanRecallAPI(true)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        navController?.let { binder.bind(it) }
        viewModel.isAppInForeground.set(true)
    }

    override fun onPause() {
        super.onPause()
        viewModel.isAppInForeground.set(false)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { ViewPumpContextWrapper.wrap(it) })
    }

    override fun onBackPressed() = Unit

    override fun instantiateDataBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun backToLogin() {
        supportActionBar?.hide()
    }

    override fun backToSplash() {
        supportActionBar?.hide()
    }

    override fun onSupportNavigateUp(): Boolean {
        when (activeFragment) {

        }
        return true
    }

    override fun setIdleTimeByUserType(time: Long) {
        applicationIdleTime = time
    }

    override fun startSessionIdleFromFragment() {

    }

    override fun showTopBar(isShow: Boolean) {
        if (isShow) supportActionBar?.show()
        else supportActionBar?.hide()
    }

    override fun setTopBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onBackNavigation(scenario: String?) {
        scenarioOnBack = scenario
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun showBackButton(isShow: Boolean) {
        if (isShow) supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        init {
            System.loadLibrary(NATIVE_LIB)
        }
    }

}