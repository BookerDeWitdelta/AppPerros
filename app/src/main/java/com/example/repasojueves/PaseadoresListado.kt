package com.example.repasojueves

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repasojueves.databinding.ActivityPaseadoresListadoBinding

class PaseadoresListado : AppCompatActivity() {
    private lateinit var binding: ActivityPaseadoresListadoBinding
    private var listap:MutableList<Paseador> = mutableListOf()
    private lateinit var  recycler:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPaseadoresListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listap.add(Paseador("Pepe","pepe@gmial.com"))
        listap.add(Paseador("Pepino","pealex@gmial.com"))
        listap.add(Paseador("Pee","pepeino@gmial.com"))
        listap.add(Paseador("Alex","pep@gmial.com"))
        listap.add(Paseador("Josue","pepesd@gmial.com"))
        agregaradaptador()
    }
    private fun agregaradaptador(){
        recycler=binding.listrecycler
        recycler.layoutManager=LinearLayoutManager(this)
        recycler.adapter=AdaptadorPaseador(this,listap)
    }
}