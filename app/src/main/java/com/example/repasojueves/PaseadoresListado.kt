package com.example.repasojueves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repasojueves.databinding.ActivityPaseadoresListadoBinding
import com.example.repasojueves.model.PaseadorRecycler
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class PaseadoresListado : AppCompatActivity() {
    private lateinit var bindin:ActivityPaseadoresListadoBinding
    private var listap:MutableList<PaseadorRecycler> = mutableListOf()
    private lateinit var recycler: RecyclerView
    private lateinit var db:FirebaseFirestore
    private lateinit var ap:AdaptadorPaseador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindin=ActivityPaseadoresListadoBinding.inflate(layoutInflater)
        setContentView(bindin.root)
        /*listap.add(Paseador("Pepe","pepe@gmial.com"))
        listap.add(Paseador("Pepino","pealex@gmial.com"))
        listap.add(Paseador("Pee","pepeino@gmial.com"))
        listap.add(Paseador("Alex","pep@gmial.com"))
        listap.add(Paseador("Josue","pepesd@gmial.com"))
        agregaradaptador()*/

        // EDITADO POR JULIANA - FIREBASE
        db=FirebaseFirestore.getInstance()
        db.collection("paseadores").addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    println("Error en Firebase")
                }
                    for(pas:DocumentChange in value?.documentChanges!!){
                        if(pas.type==DocumentChange.Type.ADDED){
                            listap.add(pas.document.toObject(PaseadorRecycler::class.java))
                        }
                    }
                ap.notifyDataSetChanged()
            }
        })
        recycler=bindin.listrecycler
        recycler.layoutManager=LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        listap= mutableListOf()
        ap=AdaptadorPaseador(this,listap !!, object : ClickListener{
            override fun OnClick(vista: View, posicion: Int) {
                Toast.makeText(applicationContext,listap?.get(posicion)?.nombre,Toast.LENGTH_LONG).show()
                val nom=listap?.get(posicion)?.nombre
                val ape=listap?.get(posicion)?.apellido
                val corr=listap?.get(posicion)?.correo
                val cel=listap?.get(posicion)?.celular
                val des=listap?.get(posicion)?.descripcion
                val img=listap?.get(posicion)?.imagen
                intent.putExtra("n",nom)
                intent.putExtra("a",ape)
                intent.putExtra("e",corr)
                intent.putExtra("c",cel)
                intent.putExtra("d",des)
                intent.putExtra("i",img)
                startActivity(intent)
            }

        })
        recycler.adapter=ap
    }
    /*private fun agregaradaptador(){
        recycler=bindin.listarecycler
        recycler.layoutManager=LinearLayoutManager(this)
        recycler.adapter=AdaptadorPaseador(this,listap)
    }*/
}