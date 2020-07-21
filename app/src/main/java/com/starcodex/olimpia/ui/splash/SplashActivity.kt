package com.starcodex.olimpia.ui.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.starcodex.olimpia.R
import com.starcodex.olimpia.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lottieAnimationView.enableMergePathsForKitKatAndAbove(true)
        lottieAnimationView.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                goToNextScreen()
            }
        })
    }

    private fun goToNextScreen() {
        startActivity(Intent(this, HomeActivity::class.java) )
        finish()
    }
}