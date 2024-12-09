package com.edmalyon.sugarblood.data.local.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.edmalyon.sugarblood.data.local.database.entities.Glucosa
import com.edmalyon.sugarblood.data.local.database.entities.Usuario


data class UsuarioConGlucosa(
    @Embedded val usuario: Usuario,
    @Relation(
        parentColumn = "id_usuario",
        entityColumn = "id_usuario"
    )
    val glucosa: List<Glucosa>
)
