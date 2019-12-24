package br.com.asoncs.service

import android.app.Activity
import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.VibrationEffect.*
import android.os.Vibrator

enum class ServiceActions {
    LIGHT_TOGGLE,
    TV_C_TOGGLE,
    TV_CHANNEL_DOWN,
    TV_CHANNEL_UP,
    TV_VOLUME_DOWN,
    TV_VOLUME_UP,
    TV_C_TOOLS,
    TV_C_APPS,
    TV_C_ENTER,
    TV_C_MENU,
    TV_C_RETURN,
    TV_C_UP,
    TV_C_LEFT,
    TV_C_RIGHT,
    TV_C_DOWN,
}

private const val VIBRATION_DURATION = 100L

fun doVibration(context: Context?) {
    context?.apply {
        val vibrator = (getSystemService(Activity.VIBRATOR_SERVICE) as Vibrator)
            .takeIf { it.hasVibrator() }
            ?.takeIf { VERSION.SDK_INT >= VERSION_CODES.O }
        vibrator?.apply {
            when {
                VERSION.SDK_INT >= VERSION_CODES.Q -> {
                    vibrate(createPredefined(EFFECT_CLICK))
                }
                VERSION.SDK_INT >= VERSION_CODES.O -> {
                    vibrate(createOneShot(VIBRATION_DURATION, DEFAULT_AMPLITUDE))
                }
                else -> {
                    @Suppress("DEPRECATION")
                    vibrate(VIBRATION_DURATION)
                }
            }
        }
    }
}