package com.starcodex.olimpia.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.starcodex.olimpia.R
import com.starcodex.olimpia.ui.stepform.StepContainerActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun navigateToStepContainer(view: View){
        startActivity(Intent(view.context, StepContainerActivity::class.java))
        finish()
    }
}