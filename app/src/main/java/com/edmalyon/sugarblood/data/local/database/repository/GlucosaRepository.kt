package com.edmalyon.sugarblood.data.local.database.repository

import com.edmalyon.sugarblood.data.local.database.dao.GlucosaDao
import com.edmalyon.sugarblood.data.local.database.entities.Glucosa
import javax.inject.Inject

class GlucosaRepository @Inject constructor(
    private val glucosaDao: GlucosaDao
) {

        suspend fun insertarGlucosa(glucosa: Glucosa) {
            glucosaDao.insertar(glucosa)
        }

        suspend fun actualizarGlucosa(glucosa: Glucosa) {
            glucosaDao.actualizar(glucosa)
        }

        suspend fun eliminarGlucosa(glucosa: Glucosa) {
            glucosaDao.borrar(glucosa)
        }

        suspend fun obtenerGlucosa(id_glucosa: Int): Glucosa? {
            return glucosaDao.obtener(id_glucosa)
        }

        suspend fun obtenerTodasGlucosa(): List<Glucosa> {
            return glucosaDao.obtenerTodos()
        }

        suspend fun obtenerGlucosaPorUsuario(id_usuario: Int): List<Glucosa> {
            return glucosaDao.obtenerPorUsuario(id_usuario)
        }


}