package com.edmalyon.sugarblood.data.local.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UsuarioInformacion(
    @Embedded val usuario: Usuario,
    @Relation(
        parentColumn = "id_usuario",
        entityColumn = "id_usuario"
    )
    val informacion: Informacion?
)
