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

class Registro : AppCompatActivity() {
    private lateinit var binding:ActivityRegistroBinding
    private lateinit var database:UsuarioDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database= Room.databaseBuilder(application,UsuarioDB::class.java,UsuarioDB.DATABASE_NAME).allowMainThreadQueries().build()

        binding=ActivityRegistroBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.btnircaptura.setOnClickListener{
            startActivity(Intent(this,Foto::class.java))
        }
        binding.btnregistrarusuario.setOnClickListener{
            //guardar()
            guardarroom()
        }
    }
    fun guardar(){
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
    }
}