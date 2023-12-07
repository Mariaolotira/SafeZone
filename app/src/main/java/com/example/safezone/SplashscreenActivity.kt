package com.example.safezone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {
    private var splashscreentimeout: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

       Handler().postDelayed({
           onLoginSuccess()

       },splashscreentimeout)


    }

    private fun onLoginSuccess() {
        var gotohome = Intent(this, HomeActivity::class.java)
        startActivity(gotohome)
        finish()
    }
}