package com.tesji.proyecto_firebase_menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class UpdateFragment : Fragment() {

    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()

        val txtIdQ = view.findViewById<TextInputEditText>(R.id.txtIdQ)
        val txtTrackQ = view.findViewById<TextInputEditText>(R.id.txtTrackQ)
        val txtArtistQ = view.findViewById<TextInputEditText>(R.id.txtArtistQ)
        val txtAlbumQ = view.findViewById<TextInputEditText>(R.id.txtAlbumQ)
        val txtGenderQ = view.findViewById<TextInputEditText>(R.id.txtGenderQ)
        val txtYearQ = view.findViewById<TextInputEditText>(R.id.txtYearQ)

        val btnQuery = view.findViewById<Button>(R.id.btnQuery)
        val btnUpdate = view.findViewById<Button>(R.id.btnUpdate)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)
        val btnClean = view.findViewById<Button>(R.id.btnClean)

        //val trackId = arguments?.getString("id")
        //txtIdQ.setText(trackId)

        btnQuery.setOnClickListener {
            db.collection("tracks").document(txtIdQ.text.toString()).get()
                .addOnSuccessListener { documentSnapshot ->
                    val documentId = documentSnapshot.id
                    Toast.makeText(requireContext(), "ID del documento: $documentId", Toast.LENGTH_SHORT).show()
                    txtTrackQ.setText(documentSnapshot.get("nombre") as String?)
                    txtArtistQ.setText(documentSnapshot.get("artista") as String?)
                    txtAlbumQ.setText(documentSnapshot.get("album") as String?)
                    txtGenderQ.setText(documentSnapshot.get("genero") as String?)
                    val yearValue = documentSnapshot.get("ano") as? Long
                    txtYearQ.setText(yearValue?.toString() ?: "")
                }
        }

        btnUpdate.setOnClickListener {
            db.collection("tracks").document(txtIdQ.text.toString()).set(
                hashMapOf(
                    "nombre" to txtTrackQ.text.toString(),
                    "artista" to txtArtistQ.text.toString(),
                    "album" to txtAlbumQ.text.toString(),
                    "genero" to txtGenderQ.text.toString(),
                    "ano" to txtYearQ.text.toString().toInt()
                )
            )
        }

        btnDelete.setOnClickListener {
            db.collection("tracks").document(txtIdQ.text.toString()).delete()
        }

        btnClean.setOnClickListener {
            txtIdQ.setText(" ")
            txtTrackQ.setText(" ")
            txtArtistQ.setText(" ")
            txtAlbumQ.setText(" ")
            txtGenderQ.setText(" ")
            txtYearQ.setText(" ")
        }
    }
}
