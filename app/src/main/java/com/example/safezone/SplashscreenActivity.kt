package com.example.safezone

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashscreenActivity : AppCompatActivity() {
    private var splashscreentimeout: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

       Handler().postDelayed({
           startActivity(Intent(this, MainActivity::class.java))
           finish()

       },splashscreentimeout)


    }
}