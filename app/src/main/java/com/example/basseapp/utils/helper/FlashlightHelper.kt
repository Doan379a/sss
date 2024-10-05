package com.example.basseapp.utils.helper

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager

class FlashlightHelper(val context: Context) {
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    private var isFlashlightOn = false

    fun turnOnFlashlight(): Boolean {
        try {
            if (!isFlashlightOn) {
                val cameraId = cameraManager.cameraIdList[0]
                cameraManager.setTorchMode(cameraId, true)
                isFlashlightOn = true
                return true
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
        return false
    }

    fun turnOffFlashlight(): Boolean {
        try {
            if (isFlashlightOn) {
                val cameraId = cameraManager.cameraIdList[0]
                cameraManager.setTorchMode(cameraId, false)
                isFlashlightOn = false
                return true
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
        return false
    }

    fun isFlashlightOn(): Boolean {
        return isFlashlightOn
    }

}