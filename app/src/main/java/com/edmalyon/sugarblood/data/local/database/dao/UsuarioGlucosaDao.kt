package com.edmalyon.sugarblood.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.edmalyon.sugarblood.data.local.database.entities.UsuarioGlucosa

@Dao
interface UsuarioGlucosaDao {
    @Transaction
    @Query("SELECT * FROM usuario WHERE id_usuario = :id_usuario")
    suspend fun getUsuarioGlucosa(id_usuario: Int): UsuarioGlucosa?

}