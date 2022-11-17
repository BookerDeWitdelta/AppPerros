package com.example.repasojueves.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Archivo creado por Juliana

@Entity(tableName = "usuarios")

class UsuarioModelo (
    @PrimaryKey
    val user:String,
    @ColumnInfo(name="nombre")
    val nombre: String,
    @ColumnInfo(name="apellido")
    val apellido:String,
    @ColumnInfo(name="correo")
    val correo:String,
    @ColumnInfo(name="celular")
    val celular:String,
    @ColumnInfo(name="clave")
    val clave:String,
)