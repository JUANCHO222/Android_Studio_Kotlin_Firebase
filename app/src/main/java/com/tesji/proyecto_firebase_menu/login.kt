package com.tesji.proyecto_firebase_menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val btnGenerar = findViewById<TextView>(R.id.btnCrearCuenta)
        val txtEmail = findViewById<TextInputEditText>(R.id.txtEmail)
        val txtContra = findViewById<TextInputEditText>(R.id.txtPassword)
        val btnOlvide = findViewById<TextView>(R.id.btn_olvidar)
        firebaseAuth = Firebase.auth

        btnIngresar.setOnClickListener()
        {
            if (txtEmail.text.toString().trim().isEmpty() && txtContra.text.toString().trim().isEmpty()) {
                txtEmail.setError("Ingrese su email")
                txtContra.setError("Ingresa la contraseña")

                return@setOnClickListener
            }else if (txtContra.text.toString().trim().isEmpty()){
                txtContra.setError("Ingresa la contraseña")
                return@setOnClickListener
            }else{
                signIn(txtEmail.text.toString(),txtContra.text.toString())
            }

        }
        btnGenerar.setOnClickListener()
        {
            val i = Intent(this, createAccount::class.java)
            startActivity(i)
        }
        btnOlvide.setOnClickListener(){
            val i = Intent(this, forgotPassword::class.java)
            startActivity(i)
        }
    }

    // Funcion signin
    private fun signIn(email: String, password: String)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    //current user usuario actual
                    val verifica = user?.isEmailVerified

                    if (verifica==true)
                    {
                        Toast.makeText(baseContext,"Exito", Toast.LENGTH_SHORT).show()
                        //Aqui vamos ir a la segunda activity
                        val i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                    }
                    else
                    {
                        Toast.makeText(baseContext,"No se ha verificado su correo", Toast.LENGTH_SHORT).show()
                    }

                }
                else
                {
                    Toast.makeText(baseContext,"Error de email y/o contraseña", Toast.LENGTH_SHORT).show()
                }
            }
    }
}