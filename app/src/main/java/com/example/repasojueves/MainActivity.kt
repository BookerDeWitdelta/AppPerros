package com.example.repasojueves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.repasojueves.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme((R.style.Theme_RepasoJueves))
        Thread.sleep(2000)
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.btnsomos.setOnClickListener{
            startActivity(Intent(this,Somos::class.java))
        }
        binding.btningresar.setOnClickListener{
            startActivity(Intent(this,Login::class.java))
        }
        binding.btnregistrarse.setOnClickListener{
            startActivity(Intent(this,RegistroUsuarioLogin::class.java)) // EDITADO JULIANA - FIREBASE
        }
    }
}