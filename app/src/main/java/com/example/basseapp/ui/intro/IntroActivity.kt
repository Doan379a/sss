package com.example.basseapp.ui.intro

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.basseapp.MainActivity
import com.example.basseapp.R
import com.example.basseapp.base.BaseActivity
import com.example.basseapp.databinding.ActivityIntroBinding
import com.example.basseapp.model.IntroModel
import com.example.basseapp.sharePreferent.SharePrefUtils
import com.example.basseapp.ui.permission.PermissionActivity

class IntroActivity : BaseActivity<ActivityIntroBinding>() {

    var isFirst = true
    private lateinit var dots: Array<ImageView?>
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val myPageChangeCallback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (isFirst) {
                    isFirst = false
                    return
                }
                addBottomDots(position)
            }
        }
    private val countPageIntro = 3

    override fun setViewBinding(): ActivityIntroBinding {
        return ActivityIntroBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        val data = mutableListOf(
            IntroModel(
                R.drawable.ic_intro_1,
                R.string.title_intro_1,
                R.string.content_intro_1
            ),
            IntroModel(
                R.drawable.ic_intro_2,
                R.string.title_intro_2,
                R.string.content_intro_2
            ),
            IntroModel(
                R.drawable.ic_intro_3,
                R.string.title_intro_3,
                R.string.content_intro_3
            )
        )
        viewPagerAdapter = ViewPagerAdapter(this, data)
        binding.viewPager2.apply {
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(myPageChangeCallback)
        }
        addBottomDots(0)
    }

    override fun viewListener() {
        binding.btnNextTutorial.setOnClickListener {
            if (binding.viewPager2.currentItem == countPageIntro - 1) {
                it.isEnabled = false
                startNextScreen()
            } else
                binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
        }
    }

    private fun startNextScreen() {
        if (SharePrefUtils.isGoToMain(this))
            showActivity(MainActivity::class.java)
        else
            showActivity(PermissionActivity::class.java)
        finishAffinity()
    }

    override fun dataObservable() {
        addBottomDots(0)
    }


    private fun addBottomDots(currentPage: Int) {
        binding.linearDots.removeAllViews()
        dots = arrayOfNulls(countPageIntro)
        for (i in 0 until countPageIntro) {
            dots[i] = ImageView(this)
            if (i == currentPage)
                dots[i]!!.setImageResource(R.drawable.ic_intro_selected)
            else
                dots[i]!!.setImageResource(R.drawable.ic_intro_not_select)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            binding.linearDots.addView(dots[i], params)
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}