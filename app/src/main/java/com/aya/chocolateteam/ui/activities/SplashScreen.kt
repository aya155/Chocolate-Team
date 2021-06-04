package com.aya.chocolateteam.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // Start home activity
//        startActivity(Intent(this@SplashActivity, TabActivity::class.java))
//        // close splash activity
//        finish()
//    }
}


//class SplashScreen : BaseActivity<ActivitySplashScreenBinding>() {
//
//    private lateinit var topAnimation: Animation
//    private lateinit var bottomAnimation: Animation
//
//    private val SPLASH_TIME: Long = 1350
//
//    override val LOG_TAG: String = "SPLASH_ACTIVITY"
//    override val bindingInflater: (LayoutInflater) -> ActivitySplashScreenBinding = ActivitySplashScreenBinding::inflate
//
//    override fun setup() {
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this, TabActivity::class.java))
//            finish()
//        }, SPLASH_TIME)
//        // set layout of splash screen
//        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
//        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.buttom_animation)
//        binding?.apply {
//            earth.animation = bottomAnimation
//            text1.animation = topAnimation
//            text2.animation = topAnimation
//            progressBar.animation = topAnimation
//        }
//    }
//    override fun addCallBack() {
//    }
//
//    override fun bindLayout(country: Country) {
//    }
//}