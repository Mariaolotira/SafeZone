package com.example.safezone

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {
    private lateinit var btnemergencycallhome : Button
    private lateinit var btntorch: Button
    private lateinit var btnlogindirect: ImageButton
    private lateinit var areabtn: TextView
    private var isFlashOn = false
    private var cameraId: String? = null
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnemergencycallhome = findViewById(R.id.btnemergencycallhome)
        btntorch= findViewById(R.id.torchbtn)
        btnlogindirect= findViewById(R.id.btnlogindirect)
        areabtn= findViewById(R.id.areabtn)
        isFlashOn = false

        btnemergencycallhome.setOnClickListener {
            val postedby = "911"

            val uri = "tel:" + postedby.trim()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse(uri)
            startActivity(intent)
        }

        // Check if device support flashlight or not
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            cameraId = cameraManager.cameraIdList[0]
        } catch (e: CameraAccessException) {
            Toast.makeText(this, "Error getting camera ID.", Toast.LENGTH_SHORT).show()
        }

        if (cameraId == null) {
            Toast.makeText(this, "Device does not support flashlight!", Toast.LENGTH_SHORT).show()
            btntorch.isEnabled = false
        }

        btntorch.setOnClickListener {
            if (btntorch.isClickable) {
                turnOnTorch()
            } else {
                turnOffTorch()
            }
        }

        btnlogindirect.setOnClickListener {
            var gotologinpage = Intent(this, MainActivity::class.java)
            startActivity(gotologinpage)
        }

        areabtn.setOnClickListener {
            var gotosafezone = Intent(this, safezone::class.java)
            startActivity(gotosafezone)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun turnOnTorch() {
        if (cameraId == null) {
            return
        }
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            cameraManager.setTorchMode(cameraId!!, true)
        } catch (e: CameraAccessException) {
            Toast.makeText(this, "Error turning on torch.", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun turnOffTorch() {
        if (cameraId == null) {
            return
        }
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            cameraManager.setTorchMode(cameraId!!, false)
        } catch (e: CameraAccessException) {
            Toast.makeText(this, "Error turning off torch.", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPause() {
        super.onPause()
        turnOffTorch()
    }
}