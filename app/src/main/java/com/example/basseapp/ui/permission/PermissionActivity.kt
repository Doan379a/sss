package com.example.basseapp.ui.permission

import com.example.basseapp.MainActivity
import com.example.basseapp.base.BaseActivity
import com.example.basseapp.databinding.ActivityPermissionBinding
import com.example.basseapp.sharePreferent.SharePrefUtils
import com.example.basseapp.utils.Default.CAMERA_PERMISSION
import com.example.basseapp.utils.Default.STORAGE_PERMISSION
import com.example.basseapp.widget.gone
import com.example.basseapp.widget.tap
import com.example.basseapp.widget.visible

class PermissionActivity : BaseActivity<ActivityPermissionBinding>() {
    override fun setViewBinding(): ActivityPermissionBinding {
        return ActivityPermissionBinding.inflate(layoutInflater)
    }

    override fun initView() {
        if (checkPermission(CAMERA_PERMISSION))
            allowCameraPermission()

        if (checkPermission(STORAGE_PERMISSION))
            allowStoragePermission()

    }

    override fun viewListener() {
        binding.apply {
            ivSetCameraPermission.tap {
                showDialogPermission(CAMERA_PERMISSION)
            }
            ivSetStoragePermission.tap {
                showDialogPermission(STORAGE_PERMISSION)
            }

            tvContinue.tap {
                SharePrefUtils.forceGoToMain(
                    this@PermissionActivity
                )
                showActivity(MainActivity::class.java)
                finishAffinity()
            }
        }

    }

    override fun dataObservable() {
    }

    override fun onPermissionGranted() {
        if (checkPermission(CAMERA_PERMISSION))
            allowCameraPermission()

        if (checkPermission(STORAGE_PERMISSION))
            allowStoragePermission()
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    override fun onResume() {
        if (checkPermission(STORAGE_PERMISSION))
            allowStoragePermission()
        super.onResume()
    }

    private fun allowCameraPermission() {
        binding.ivSetCameraPermission.gone()
        binding.ivSelectCameraPermission.visible()
    }

    private fun allowStoragePermission() {
        binding.ivSetStoragePermission.gone()
        binding.ivSelectStoragePermission.visible()
    }
}