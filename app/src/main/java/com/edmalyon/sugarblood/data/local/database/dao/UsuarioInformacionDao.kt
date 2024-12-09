package com.edmalyon.sugarblood.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.edmalyon.sugarblood.data.local.database.entities.UsuarioInformacion

@Dao
interface UsuarioInformacionDao {
    @Transaction
    @Query("SELECT * FROM usuario WHERE id_usuario = :id_usuario")
    suspend fun getUsuarioInformacion(id_usuario: Int): UsuarioInformacion?

}