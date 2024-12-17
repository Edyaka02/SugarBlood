package com.edmalyon.sugarblood.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.edmalyon.sugarblood.data.local.database.entities.Informacion

@Dao
interface InformacionDao {
    //-------------------------------------------------------------------------------------------
    // Nuevas funciones
    //-------------------------------------------------------------------------------------------

    @Insert
    suspend fun insertar(informacion: Informacion) : Long

    @Update
    suspend fun actualizar(informacion: Informacion)

    @Query("SELECT * FROM informacion WHERE id_usuario = :id_usuario")
    suspend fun obtenerPorUsuario(id_usuario: Int): List<Informacion>

    @Delete
    suspend fun borrar(informacion: Informacion)
}