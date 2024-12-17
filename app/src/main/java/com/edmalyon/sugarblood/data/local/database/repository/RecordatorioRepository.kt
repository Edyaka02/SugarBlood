package com.edmalyon.sugarblood.data.local.database.repository

import com.edmalyon.sugarblood.data.local.database.dao.RecordatorioDao
import com.edmalyon.sugarblood.data.local.database.entities.Recordatorio

class RecordatorioRepository(
    private val recordatorioDao: RecordatorioDao
) {

        suspend fun insertarRecordatorio(recordatorio: Recordatorio) {
            recordatorioDao.insertar(recordatorio)
        }

        suspend fun actualizarRecordatorio(recordatorio: Recordatorio) {
            recordatorioDao.actualizar(recordatorio)
        }

        suspend fun eliminarRecordatorio(recordatorio: Recordatorio) {
            recordatorioDao.borrar(recordatorio)
        }

        suspend fun obtenerRecordatorio(id_recordatorio: Int): Recordatorio? {
            return recordatorioDao.obtener(id_recordatorio)
        }

        suspend fun obtenerTodosRecordatorio(): List<Recordatorio> {
            return recordatorioDao.obtenerTodos()
        }

        suspend fun obtenerRecordatorioPorUsuario(id_usuario: Int): List<Recordatorio> {
            return recordatorioDao.obtenerPorUsuario(id_usuario)
        }
}