package com.edmalyon.sugarblood.data.local.database.repository

import com.edmalyon.sugarblood.data.local.database.dao.InformacionDao
import com.edmalyon.sugarblood.data.local.database.entities.Informacion

class InformacionRepository(
    private val informacionDao: InformacionDao
) {

        suspend fun insertarInformacion(informacion: Informacion) {
            informacionDao.insertar(informacion)
        }

        suspend fun actualizarInformacion(informacion: Informacion) {
            informacionDao.actualizar(informacion)
        }

        suspend fun eliminarInformacion(informacion: Informacion) {
            informacionDao.borrar(informacion)
        }



        suspend fun obtenerInformacionPorUsuario(id_usuario: Int): List<Informacion> {
            return informacionDao.obtenerPorUsuario(id_usuario)
        }
}