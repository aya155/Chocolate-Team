package com.aya.chocolateteam.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.aya.chocolateteam.R

class SplashScreen : AppCompatActivity() {
    private lateinit var topAnimation: Animation
    private lateinit var buttomAnimation: Animation

    private lateinit var imageView: ImageView
    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private lateinit var progressBar: ProgressBar

    private val SPLASH_TIME: Long =6000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },SPLASH_TIME)

        topAnimation= AnimationUtils.loadAnimation(this, R.anim.top_animation)
        buttomAnimation= AnimationUtils.loadAnimation(this, R.anim.buttom_animation)

        imageView=findViewById(R.id.earth)
        text1=findViewById(R.id.text1)
        text2=findViewById(R.id.text2)
        progressBar=findViewById(R.id.progress_bar)

        imageView.animation=buttomAnimation
        text1.animation=topAnimation
        text2.animation=topAnimation
        progressBar.animation=topAnimation
    }
}