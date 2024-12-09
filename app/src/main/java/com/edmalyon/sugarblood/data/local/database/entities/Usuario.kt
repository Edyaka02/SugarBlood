package com.edmalyon.sugarblood.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id_usuario: Int = 0,
    val nombre_usuario: String,
    val email_usuario: String,

)
