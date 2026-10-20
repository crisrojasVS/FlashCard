package com.cristo.flashcard.Presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristo.flashcard.data.FlashcardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlashcardVM(private val repository: FlashcardRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(FlashcardUiState())
    val uiState: StateFlow<FlashcardUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.tarjetas.collect { lista ->
                _uiState.update { it.copy(tarjetas = lista) }
            }
        }
    }

    fun irA(pantalla: screen) {
        _uiState.update {
            it.copy(
                pantalla = pantalla,
                indiceActual = 0,
                mostrarRespuesta = false,
                mostrarError = false
            )
        }
    }

    fun voltear() {
        _uiState.update { it.copy(mostrarRespuesta = !it.mostrarRespuesta) }
    }

    fun siguiente() {
        _uiState.update {
            if (it.indiceActual < it.tarjetas.size - 1) {
                it.copy(indiceActual = it.indiceActual + 1, mostrarRespuesta = false)
            } else {
                it
            }
        }
    }

    fun anterior() {
        _uiState.update {
            if (it.indiceActual > 0) {
                it.copy(indiceActual = it.indiceActual - 1, mostrarRespuesta = false)
            } else {
                it
            }
        }
    }

    fun guardarTarjeta(pregunta: String, respuesta: String) {
        if (pregunta.isBlank() || respuesta.isBlank()) {
            _uiState.update { it.copy(mostrarError = true) }
            return
        }
        viewModelScope.launch {
            repository.agregarTarjeta(pregunta.trim(), respuesta.trim())
            irA(screen.LISTA)
        }
    }

    fun eliminarTarjeta(id: Int) {
        viewModelScope.launch {
            repository.eliminarTarjeta(id)
        }
    }
}