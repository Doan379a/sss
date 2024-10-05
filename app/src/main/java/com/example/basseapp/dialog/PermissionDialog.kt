package com.example.basseapp.dialog

import android.app.Activity
import android.view.LayoutInflater
import com.example.basseapp.base.BaseDialog
import com.example.basseapp.databinding.DialogPermissionBinding
import com.example.basseapp.widget.tap


class PermissionDialog(
    activity1: Activity,
    private var action: () -> Unit
) : BaseDialog<DialogPermissionBinding>(activity1, true) {


    override fun getContentView(): DialogPermissionBinding {
        return DialogPermissionBinding.inflate(LayoutInflater.from(activity))
    }

    override fun initView() {
    }

    override fun bindView() {
        binding.apply {
            txtGo.tap {
                action.invoke()
                dismiss()
            }
        }
    }
}
