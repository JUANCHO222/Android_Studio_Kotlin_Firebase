package com.tesji.proyecto_firebase_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class forgotPassword : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val txt_mail = findViewById<EditText>(R.id.txt_correo_recu)
        val btn_enviar = findViewById<Button>(R.id.btn_recuperacion)
        firebaseAuth = Firebase.auth

        btn_enviar.setOnClickListener()
        {
            if (txt_mail.text.toString().trim().isEmpty()){
                txt_mail.setError("Ingrese su email")
                return@setOnClickListener
            }else{
                resetCount(txt_mail.text.toString())
            }
        }
    }
    private fun resetCount(email:String)
    {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(){task ->
                if(task.isSuccessful){
                    Toast.makeText(baseContext,"Mensaje enviado de restablecimiento de contraseña", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(baseContext,"Error al enviar el restablecimiento", Toast.LENGTH_SHORT).show()

                }

            }
    }
}