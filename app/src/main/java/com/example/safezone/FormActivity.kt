package com.example.safezone

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safezone.databinding.ActivityFormBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FormActivity : AppCompatActivity() {
    private lateinit var btnback: Button
    private lateinit var btnimageform: Button
    private lateinit var ImageView: ImageView
    private lateinit var btnupload: Button
    private lateinit var btnsubmit: Button
    private lateinit var binding: ActivityFormBinding
    private lateinit var database: DatabaseReference
    private lateinit var ImagUri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnback = findViewById(R.id.btnback)
        btnimageform = findViewById(R.id.btnimageform)
        ImageView = findViewById(R.id.ImageView)
        btnupload = findViewById(R.id.btnupload)
        btnsubmit = findViewById(R.id.btnsubmit)



        binding.btnsubmit.setOnClickListener {
            val firstname = binding.edtfirstnamefrm.text.toString()
            val lastname = binding.edtlastnamefrm.text.toString()
            val idnumber = binding.edtidfrm.text.toString()
            val date = binding.edtdatefrm.text.toString()
            val location = binding.edtlocationfrm.text.toString()
            val details = binding.edtdetailsfrm.text.toString()
            val image = binding.btnimageform.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Report")
            val report = Report(firstname, lastname, idnumber, date, details, location, image)
            database.child(idnumber).setValue(report).addOnSuccessListener {
                binding.edtfirstnamefrm.text.clear()
                binding.edtlastnamefrm.text.clear()
                binding.edtidfrm.text.clear()
                binding.edtdatefrm.text.clear()
                binding.edtlocationfrm.text.clear()
                binding.edtdetailsfrm.text.clear()
                binding.btnimageform.text.javaClass

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
            }

        }

        btnback.setOnClickListener {
            val gotohome = Intent(this, HomeActivity::class.java)
            startActivity(gotohome)
        }

        binding.btnimageform.setOnClickListener {
            selectImage()
        }

        binding.btnupload.setOnClickListener {
            uploadImage()
        }

    }

    private fun uploadImage() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File ...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("Image/$fileName")

        storageReference.putFile(ImagUri).
        addOnSuccessListener {
            binding.ImageView.setImageURI(null)
            Toast.makeText(this@FormActivity, "Successfully uploaded", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()
        }.addOnFailureListener{
            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@FormActivity, "Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK){
            ImagUri = data?.data!!
            binding.ImageView.setImageURI(ImagUri)

        }
    }


}


