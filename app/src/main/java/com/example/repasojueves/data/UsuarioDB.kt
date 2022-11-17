package com.example.repasojueves.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.repasojueves.dao.UsuarioDAO
import com.example.repasojueves.model.UsuarioModelo

// Archivo creado por Juliana

@Database(
    entities=[UsuarioModelo::class],
    version = 1, exportSchema = false
)

abstract class UsuarioDB: RoomDatabase() {
    abstract val usuarioDAO:UsuarioDAO
    companion object{
        const val DATABASE_NAME="DBPaseadog"
    }

}