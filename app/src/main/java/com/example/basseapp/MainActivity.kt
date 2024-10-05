package com.example.basseapp

import com.example.basseapp.base.BaseActivity
import com.example.basseapp.databinding.ActivityMainBinding
import com.example.basseapp.ui.settings.SettingActivity
import com.example.basseapp.widget.tap

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun setViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun viewListener() {
        binding.ivSetting.tap {
            showActivity(SettingActivity::class.java)
        }
    }

    override fun dataObservable() {

    }
}