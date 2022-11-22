package com.example.repasojueves

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.repasojueves.databinding.ActivityUsuarioBinding
import com.google.firebase.firestore.FirebaseFirestore

class Usuario : AppCompatActivity() {
    private lateinit var binding:ActivityUsuarioBinding
    private val db= FirebaseFirestore.getInstance() // AGREGADO POR JULIANA - FIREBASE
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityUsuarioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // EDITADO POR JULIANA - FIREBASE
        val bundle=intent.extras
        val dato=bundle?.getString("ide") // EDITADO JULIANA - FIREBASE
        consultarusuario(dato ?:"")

        //obtenerdatos()
        // EDITADO POR JULIANA - FIREBASE
        binding.btnConsultaPaseadores.setOnClickListener{
            startActivity(Intent(this,PaseadoresListado::class.java))
        }
    }
    fun obtenerdatos(){
        val cajatxt=binding.viewnombre
        val datos=getSharedPreferences("bdusuario", Context.MODE_PRIVATE)
        val nombre=datos.getString("nombre","")
        cajatxt.setText(nombre)
    }

    // AGREGADO POR JULIANA - FIREBASE
    fun consultarusuario(id:String){
        db.collection("usuarios").document(id).get().addOnSuccessListener {
            binding.viewnombre.setText(it.get("nombre") as String? + " " + it.get("apellido") as String?)
            binding.viewcorreo.setText(it.get("correo") as String?)
            binding.viewtelefono.setText(it.get("telefono") as String?)


        }
    }
}