package com.example.repasojueves

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.repasojueves.data.UsuarioDB
import com.example.repasojueves.databinding.ActivityFotoBinding
import com.example.repasojueves.databinding.ActivityRegistroBinding
import com.example.repasojueves.model.UsuarioModelo
import com.google.firebase.firestore.FirebaseFirestore

class Registro : AppCompatActivity() {
    private lateinit var binding:ActivityRegistroBinding
    private lateinit var database:UsuarioDB
    private val db=FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistroBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val bundle=intent.extras
        val email:String?=bundle?.getString("email")
        binding.correo.setText(email)

        //val room = Room.databaseBuilder(this,UsuarioDB::class.java,"Paseadores").build()
        //database= Room.databaseBuilder(application,UsuarioDB::class.java,UsuarioDB.DATABASE_NAME).allowMainThreadQueries().build()

        binding.btnircaptura.setOnClickListener{
            startActivity(Intent(this,Foto::class.java))
        }
        binding.btnregistrarusuario.setOnClickListener{
            //guardar()
            //guardarroom()
            val bundle=intent.extras
            val datoid:String?=bundle?.getString("id")
            guardardatosfirestore(datoid ?:"")
            Toast.makeText(this,"Datos guardados",Toast.LENGTH_LONG).show()
        }
    }
    /*fun guardar(){
        val txtnombre=binding.nombre.text.toString()
        val txtapellido=binding.apellido.text.toString()
        val txtcorreo=binding.correo.text.toString()
        val txtcelular=binding.telefono.text.toString()
        val txtusuario=binding.usuario.text.toString()
        val txtclave=binding.clave.text.toString()
        val datos=getSharedPreferences("bdusuario",Context.MODE_PRIVATE)
        val editor=datos.edit()
        editor.putString("nombre",txtnombre)
        editor.putString("apellido",txtapellido)
        editor.putString("correo",txtcorreo)
        editor.putString("celular",txtcelular)
        editor.putString("usuario",txtusuario)
        editor.putString("clave",txtclave)
        editor.commit()
        Toast.makeText(this,"Datos guardados",Toast.LENGTH_LONG).show()
    }

    // Creado por Juliana - ROOM
    fun guardarroom(){
        val txtnombre=binding.nombre.text.toString()
        val txtapellido=binding.apellido.text.toString()
        val txtcorreo=binding.correo.text.toString()
        val txtcelular=binding.telefono.text.toString()
        val txtusuario=binding.usuario.text.toString()
        val txtclave=binding.clave.text.toString()
        val user=UsuarioModelo(txtusuario,txtnombre,txtapellido,txtcorreo,txtcelular,txtclave)
        database.usuarioDAO.insertar(user)
    }*/

    // Creado por Juliana - FIREBASE
    private fun guardardatosfirestore(datoid:String){
        db.collection("usuarios").document(datoid).set(
            hashMapOf("nombre" to binding.nombre.text.toString(),"apellido" to binding.apellido.text.toString(),"correo" to binding.correo.text.toString(),"celular" to binding.telefono.text.toString())
        )
    }
}