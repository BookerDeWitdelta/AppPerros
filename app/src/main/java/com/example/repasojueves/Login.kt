package com.example.repasojueves

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.repasojueves.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.btnlogin.setOnClickListener {
            validar()
        }
        binding.txtrecuperar.setOnClickListener {
            recuperar()
        }
    }
    fun validar(){
        val txtusu=binding.txtusuario.text.toString()
        val txtcla=binding.txtclave.text.toString()
        val datos=getSharedPreferences("bdusuario",Context.MODE_PRIVATE)
        val usu=datos.getString("usuario","")
        val cla=datos.getString("clave","")
        if(txtusu.isEmpty()){
            binding.txtusuario.setHint("Ingresar el usuario")
        }

        else if(txtcla.isEmpty()){
            binding.txtclave.setHint("Ingresar clave")
            binding.txtclave.setHintTextColor(Color.RED)
        }
        else if(txtusu.equals(usu)&& txtcla.equals(cla)){
            Toast.makeText(this,"Datos correctos",Toast.LENGTH_LONG).show()
            val intent=Intent(this,Usuario::class.java)
            intent.putExtra("nombre",usu)
            startActivity(intent)
        }
        else{
            Toast.makeText(this,"Sus datos no son correctos",Toast.LENGTH_LONG).show()
        }
    }
    fun recuperar(){
        val intent=Intent(this,RecuperarContrase::class.java)
        startActivity(intent)
    }
}
