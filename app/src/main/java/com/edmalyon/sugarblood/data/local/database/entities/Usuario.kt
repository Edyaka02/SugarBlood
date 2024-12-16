package com.edmalyon.sugarblood.data.local.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "usuario",
    indices = [Index(value = ["nombre_usuario"], unique = true)]
)
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id_usuario: Int = 0,
    val nombre_usuario: String,
    val password_usuario: String,
    val password_confirmar_usuario: String

)
