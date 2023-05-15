package com.example.safezone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
     lateinit var edtemaillogin: EditText
     lateinit var edtpasswordlogin: EditText
     lateinit var btnloginlog: Button
     lateinit var btnregisterlog:Button

     //initialize firebase
     lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtemaillogin = findViewById(R.id.edtemaillogin)
        edtpasswordlogin = findViewById(R.id.edtpasswordlogin)
        btnloginlog = findViewById(R.id.btnloginlog)
        btnregisterlog = findViewById(R.id.btnregisterlog)

        auth = FirebaseAuth.getInstance()

        btnloginlog.setOnClickListener {
            var email = edtemaillogin.text.toString().trim()
            var password = edtpasswordlogin.text.toString().trim()

            //validate inputs

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "One of the Inputs is Empty!!", Toast.LENGTH_SHORT).show()

            }else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                        var gotohome = Intent(this, HomeActivity::class.java)
                        startActivity(gotohome)
                        finish()
                    }else{
                        Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnregisterlog.setOnClickListener {
            var gotoregister = Intent(this, RegistrationActivity::class.java)
            startActivity(gotoregister)
            finish()
        }
    }
}