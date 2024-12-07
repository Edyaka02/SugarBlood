package com.edmalyon.sugarblood.data.local.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "informacion",
    foreignKeys = [ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Informacion(
    val id_salud: Int,
    val nombre: String,
    val apellido: String,
    val altura: Double,
    val edad: Int,
    val sexo: String,
    val id_usuario: Int
)
