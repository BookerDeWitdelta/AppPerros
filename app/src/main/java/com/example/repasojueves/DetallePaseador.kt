package com.example.repasojueves

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.repasojueves.databinding.ActivityDetallePaseadorBinding

class DetallePaseador : AppCompatActivity() {
    private lateinit var binding: ActivityDetallePaseadorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityDetallePaseadorBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bundle=intent.extras
        val nombre=bundle?.getString("n")
        val apellido=bundle?.getString("a")
        val correo=bundle?.getString("e")
        val celular=bundle?.getString("c")
        val descripcion=bundle?.getString("d")
        val imagen=bundle?.getString("i")
        binding.txtnombrecompleto.setText(nombre+" "+apellido)
        binding.txtcelular.setText(celular)
        binding.txtcorreo.setText(correo)
        binding.txtdescripcion.setText(descripcion)
        Glide.with(this).load(imagen).into(binding.imgfoto)
    }
}