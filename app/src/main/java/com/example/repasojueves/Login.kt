package com.example.repasojueves

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.repasojueves.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var firebaseauth:FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    private val bd= FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        firebaseauth= Firebase.auth // EDITADO POR JULIANA - FIREBASE
        binding.btnlogin.setOnClickListener {
            login(binding.txtusuario.text.toString(),binding.txtclave.text.toString()) // EDITADO POR JULIANA - FIREBASE
        }
        binding.btnrecuperarclave.setOnClickListener{
            startActivity(Intent(this,RecuperarContrase::class.java))
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

    // EDITADO POR JULIANA - FIREBASE
    private fun login(email:String,password:String){
        firebaseauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){task->
            if (task.isSuccessful){
                val id=firebaseauth.uid
                Toast.makeText(this,"Datos correctos",Toast.LENGTH_LONG).show()
                val intent=Intent(this,Usuario::class.java)
                intent.putExtra("ide",id)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"El usuario no se encontró",Toast.LENGTH_LONG).show()

            }
        }

    }

    private fun consultardatos(id:String){
        bd.collection("usuarios")
    }
}