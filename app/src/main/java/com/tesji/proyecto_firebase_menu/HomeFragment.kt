package com.tesji.proyecto_firebase_menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        db = Firebase.firestore

        val txtId = view.findViewById<TextInputEditText>(R.id.txtId)
        val txtTrack = view.findViewById<TextInputEditText>(R.id.txtTrack)
        val txtArtist = view.findViewById<TextInputEditText>(R.id.txtArtist)
        val txtAlbum = view.findViewById<TextInputEditText>(R.id.txtAlbum)
        val txtGender = view.findViewById<TextInputEditText>(R.id.txtGender)
        val txtYear = view.findViewById<TextInputEditText>(R.id.txtYear)

        val btnClean = view.findViewById<MaterialButton>(R.id.btnClean)
        val btnInsert = view.findViewById<MaterialButton>(R.id.btnInsert)



        btnInsert.setOnClickListener {
            db.collection("tracks").document(txtId.text.toString()).set(
                hashMapOf(
                    "id" to txtId.text.toString(),
                    "nombre" to txtTrack.text.toString(),
                    "artista" to txtArtist.text.toString(),
                    "album" to txtAlbum.text.toString(),
                    "genero" to txtGender.text.toString(),
                    "ano" to txtYear.text.toString().toInt()
                )
            )
        }


        btnClean.setOnClickListener {
            txtId.setText(" ")
            txtTrack.setText(" ")
            txtArtist.setText(" ")
            txtAlbum.setText(" ")
            txtGender.setText(" ")
            txtYear.setText(" ")
        }

        return view
    }

}


