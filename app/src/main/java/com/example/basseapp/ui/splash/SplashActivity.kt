package com.example.basseapp.ui.splash

import android.annotation.SuppressLint
import android.view.LayoutInflater
import com.example.basseapp.base.BaseActivity
import com.example.basseapp.databinding.ActivitySplashBinding
import com.example.basseapp.ui.language_start.LanguageStartActivity
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default);

    //    var adCallback: AdCallback? = null
    override fun setViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        coroutineScope.launch {
            delay(4000)
            showActivity(LanguageStartActivity::class.java)
        }
    }

    override fun viewListener() {
    }

    override fun dataObservable() {
    }

    override fun onBackPressed() {
    }

    override fun onResume() {
        super.onResume()
    }
}