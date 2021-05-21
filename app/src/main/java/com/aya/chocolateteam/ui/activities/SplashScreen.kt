package com.aya.chocolateteam.ui.activities

import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.aya.chocolateteam.R
import com.aya.chocolateteam.databinding.ActivitySplashScreenBinding

class SplashScreen : BaseActivity<ActivitySplashScreenBinding>() {

    private lateinit var topAnimation: Animation
    private lateinit var bottomAnimation: Animation

    private val SPLASH_TIME: Long = 1000

    override val LOG_TAG: String = "SPLASH_ACTIVITY"
    override val bindingInflater: (LayoutInflater) -> ActivitySplashScreenBinding = ActivitySplashScreenBinding::inflate

    override fun setup() {
        Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
        }, SPLASH_TIME)
    }

    override fun addCallBack() {
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.buttom_animation)
        binding?.apply {
            earth.animation = bottomAnimation
            text1.animation = topAnimation
            text2.animation = topAnimation
            progressBar.animation = topAnimation
        }
    }
}