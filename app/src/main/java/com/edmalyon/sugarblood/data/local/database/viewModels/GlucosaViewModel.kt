package com.edmalyon.sugarblood.data.local.database.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edmalyon.sugarblood.data.local.database.entities.Glucosa
import com.edmalyon.sugarblood.data.local.database.repository.GlucosaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlucosaViewModel @Inject constructor(
    private val glucosaRepository: GlucosaRepository
) : ViewModel() {

    private val _glucosaList = mutableStateOf<List<Glucosa>>(emptyList())
    val glucosaList: State<List<Glucosa>> get() = _glucosaList

    fun obtenerGlucosaPorUsuario(usuarioId: Int) {
        viewModelScope.launch {
            val glucosaObtenida = glucosaRepository.obtenerGlucosaPorUsuario(usuarioId)
            _glucosaList.value = glucosaObtenida
        }
    }

    fun insertarGlucosa(glucosa: Glucosa) {
        viewModelScope.launch {
            try {
                glucosaRepository.insertarGlucosa(glucosa)
            } catch (e: Exception) {
                Log.e("GlucosaViewModel", "Error registrando glucosa: ${e.message}")
            }
        }
    }

    fun eliminarGlucosa(glucosa: Glucosa) {
        viewModelScope.launch {
            glucosaRepository.eliminarGlucosa(glucosa)
            obtenerGlucosaPorUsuario(glucosa.id_usuario) // Actualiza la lista después de eliminar
        }
    }

}