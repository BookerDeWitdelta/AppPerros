package com.example.repasojueves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.repasojueves.databinding.ActivityRegistroUsuarioLoginBinding
import com.example.repasojueves.databinding.ActivityUsuarioBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistroUsuarioLogin : AppCompatActivity() {
    private lateinit var binding:ActivityRegistroUsuarioLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityRegistroUsuarioLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        firebaseAuth=Firebase.auth
        binding.btnregistrarusuariologin.setOnClickListener{
            if(binding.txtregistroclave.text.toString().equals(binding.txtconfirclave.text.toString())){
                if(binding.txtregistroclave.length()>6){
                    registrarUsuarioSesion(binding.txtregistrousus.text.toString(),binding.txtregistroclave.text.toString())
                }
                else{
                    Toast.makeText(this,"La contraseña debe tener minimo 6 caracteres",Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(this,"Las contraseñas no son iguales",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun registrarUsuarioSesion(email:String,clave:String){
        if(email.isNotEmpty() && clave.isNotEmpty()){
            firebaseAuth.createUserWithEmailAndPassword(email,clave).addOnCompleteListener(this){
                    task->
                if(task.isSuccessful){
                    val id=firebaseAuth.uid
                    val intent=Intent(this,Registro::class.java)
                    intent.putExtra("id",id)
                    intent.putExtra("email",email)
                    Toast.makeText(this,"Datos guardados",Toast.LENGTH_LONG).show()
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Hubo un error",Toast.LENGTH_LONG).show()
                }
            }
        }
        else{
            Toast.makeText(this,"Los campos deben tener datos",Toast.LENGTH_LONG).show()
        }
    }
}