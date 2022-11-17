package com.example.repasojueves.dao

import androidx.room.*
import com.example.repasojueves.model.UsuarioModelo

// Archivo creado por Juliana

@Dao

interface UsuarioDAO {
    @Query("SELECT*FROM usuarios")
    fun consultar():List<UsuarioModelo>

    @Update
    fun actualizar(usuario:UsuarioModelo)

    @Insert
    fun insertar(usuario: UsuarioModelo)

    @Delete
    fun eliminar(usuario: UsuarioModelo)
}