package com.ort.seekgame.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ort.seekgame.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT : Long = 3000
    private lateinit var ivLogoSplash : ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ivLogoSplash = findViewById(R.id.ivLogoSplash);

        val anim = RotateAnimation(0f, 360f, 360f, 360f)
        anim.repeatCount = Animation.INFINITE
        anim.duration = 2000


        ivLogoSplash.startAnimation(anim);

        Handler().postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            , SPLASH_TIME_OUT)
    }
}