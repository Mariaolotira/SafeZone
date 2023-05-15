package com.example.safezone

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {
    private lateinit var btnreporthome : Button
    private lateinit var btnemergencycallhome : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnreporthome= findViewById(R.id.btnreporthome)
        btnemergencycallhome = findViewById(R.id.btnemergencycallhome)

        btnemergencycallhome.setOnClickListener {
            val postedby = "911"

            val uri = "tel:" + postedby.trim()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse(uri)
            startActivity(intent)
        }

        btnreporthome.setOnClickListener {
            val gotoform = Intent(this, FormActivity::class.java)
            startActivity(gotoform)
        }
    }
}