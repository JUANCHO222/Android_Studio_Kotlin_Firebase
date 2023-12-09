package com.tesji.proyecto_firebase_menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class createAccount : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        val txtnombre_nuevo = findViewById<EditText>(R.id.txt_usuario)
        val txt_email_nuevo = findViewById<EditText>(R.id.txt_nuevo_email)
        val txt_contrasena = findViewById<EditText>(R.id.txt_contrasena)
        val txt_conf_pass = findViewById<EditText>(R.id.txt_contrasena_confirm)
        val btn_generar = findViewById<Button>(R.id.btn_generar)

        btn_generar.setOnClickListener()
        {
            if (txtnombre_nuevo.text.toString().trim().isEmpty() || txt_email_nuevo.text.toString().trim().isEmpty()
                || txt_contrasena.text.toString().trim().isEmpty() || txt_conf_pass.text.toString().trim().isEmpty()){
                return@setOnClickListener
            }else{
                // Recibimos los datos
                val pass1 = txt_contrasena.text.toString()
                var pass2 = txt_conf_pass.text.toString()

                if (pass1.equals(pass2))
                {
                    createAccount(txt_email_nuevo.text.toString(), txt_contrasena.text.toString())
                    val i = Intent(this, login::class.java)
                    startActivity(i)
                }
                else
                {
                    Toast.makeText(baseContext,"Error: las passwords no coinciden", Toast.LENGTH_SHORT).show()
                    txt_contrasena.requestFocus()

                }
            }
        }
        firebaseAuth = Firebase.auth

    }
    private fun createAccount(email:String, password:String)
    {
        firebaseAuth.createUserWithEmailAndPassword(email, password).
        addOnCompleteListener(this){ task ->
            if(task.isSuccessful)
            {
                Toast.makeText(baseContext,"Cuenta creada correctamente, se requiere verificacion", Toast.LENGTH_SHORT).show()
                sendEmailVerification()
            }
            else
            {
                Toast.makeText(baseContext,"Error", Toast.LENGTH_SHORT).show()

            }

        }
    }
    // Correo de verificacion
    private fun sendEmailVerification()
    {
        val user = firebaseAuth.currentUser!!
        user?.sendEmailVerification()?.addOnCompleteListener(this){task->
            if(task.isSuccessful)
            {

            }
            else
            {

            }
        }
    }

}