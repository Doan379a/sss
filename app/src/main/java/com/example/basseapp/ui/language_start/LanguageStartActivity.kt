package com.example.basseapp.ui.language_start

import android.content.Intent
import com.example.basseapp.base.BaseActivity
import com.example.basseapp.databinding.ActivityLangugeStartBinding
import com.example.basseapp.model.LanguageModel
import com.example.basseapp.ui.intro.IntroActivity
import com.example.basseapp.utils.SystemUtil
import com.example.basseapp.widget.tap
import com.example.basseapp.widget.visible
import java.util.Locale

class LanguageStartActivity : BaseActivity<ActivityLangugeStartBinding>() {

    private lateinit var adapter: LanguageStartAdapter
    private var listLanguage: ArrayList<LanguageModel> = ArrayList()
    private var codeLang = ""

    override fun setViewBinding(): ActivityLangugeStartBinding {
        return ActivityLangugeStartBinding.inflate(layoutInflater)
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    override fun initView() {
        adapter = LanguageStartAdapter(this, onClick = {
            codeLang = it.code
            adapter.setCheck(it.code)
            binding.ivDone.visible()
        })
        binding.recyclerView.adapter = adapter

    }

    override fun viewListener() {
        binding.ivDone.tap {
            SystemUtil.saveLocale(baseContext, codeLang)
            startActivity(Intent(this@LanguageStartActivity, IntroActivity::class.java))
            finish()
        }
    }

    override fun dataObservable() {
        setCodeLanguage()
        initData()
    }

    private fun setCodeLanguage() {
        //language
        val codeLangDefault = Locale.getDefault().language
        val langDefault = arrayOf("fr", "pt", "es", "de", "in", "en", "hi", "vi", "ja") //"hi" ấn độ
        codeLang =
            if (SystemUtil.getPreLanguage(this).equals(""))
                if (!mutableListOf(*langDefault)
                        .contains(codeLangDefault)
                ) {
                    "en"
                } else {
                    codeLangDefault
                } else {
                SystemUtil.getPreLanguage(this)
            }
    }

    private fun initData() {
        var pos = 0
        listLanguage.clear()
        listLanguage.addAll(SystemUtil.listLanguage())
//        listLanguage.forEachIndexed { index, languageModel ->
//            if (languageModel.code == codeLang) {
//                pos = index
//                return@forEachIndexed
//            }
//        }
//        val temp = listLanguage[pos]
//        temp.active = true
//        listLanguage.removeAt(pos)
//        listLanguage.add(0, temp)
        adapter.addList(listLanguage)
    }
}