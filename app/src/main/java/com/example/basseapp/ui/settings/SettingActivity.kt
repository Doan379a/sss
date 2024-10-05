package com.example.basseapp.ui.settings

import android.content.Context
import android.content.SharedPreferences
import com.example.basseapp.base.BaseActivity
import com.example.basseapp.databinding.ActivitySettingBinding
import com.example.basseapp.sharePreferent.SharePrefUtils
import com.example.basseapp.ui.language.LanguageActivity
import com.example.basseapp.utils.HelperMenu
import com.example.basseapp.widget.gone
import com.example.basseapp.widget.tap


class SettingActivity : BaseActivity<ActivitySettingBinding>(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private var helperMenu: HelperMenu? = null

    override fun setViewBinding(): ActivitySettingBinding {
        return ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun initView() {
        if (SharePrefUtils.isRated(this))
            binding.tvRate.gone()
    }

    override fun viewListener() {
        binding.apply {
            tvRate.tap { helperMenu?.showDialogRate(false) }
            tvFeedback.tap { helperMenu?.showDialogFeedback() }
            tvShare.tap { helperMenu?.showShareApp() }
            tvPolicy.tap { helperMenu?.showPolicy() }
            tvLanguage.tap { showActivity(LanguageActivity::class.java) }
            ivBack.tap { finish() }
        }
    }

    override fun dataObservable() {
        helperMenu = HelperMenu(this)

        val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
        prefs.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == null)
            return

        if (SharePrefUtils.isRated(this))
            binding.tvRate.gone()
    }

}