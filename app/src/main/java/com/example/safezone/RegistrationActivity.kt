package com.example.safezone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {
    private lateinit var edtfirstnamereg:EditText
    private lateinit var edtsecondnamereg:EditText
    private lateinit var edtemailreg: EditText
    private lateinit var edtpasswordreg: EditText
    private lateinit var btnregisterreg: Button

    //Initialise firebase
    private lateinit var auth:FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        edtfirstnamereg = findViewById(R.id.edtfirstnamereg)
        edtsecondnamereg = findViewById(R.id.edtsecondnamereg)
        edtemailreg = findViewById(R.id.edtemailreg)
        edtpasswordreg = findViewById(R.id.edtpasswordreg)
        btnregisterreg = findViewById(R.id.btnregisterreg)

        //initialise firebase again
        auth= FirebaseAuth.getInstance()

        btnregisterreg.setOnClickListener {
            val firstname = edtfirstnamereg.text.toString().trim()
            val secondname = edtsecondnamereg.text.toString().trim()
            val email = edtemailreg.text.toString().trim()
            val password = edtpasswordreg.text.toString().trim()

            //validate inputs
            if (firstname.isEmpty() || secondname.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "One of the Fields is Empty!", Toast.LENGTH_SHORT).show()
            }else{
                //create a user in firebase
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show()
                        val gotohome = Intent(this, HomeActivity::class.java)
                        startActivity(gotohome)
                        finish()
                    }else{
                        Toast.makeText(this, "Failed to Create Account", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}