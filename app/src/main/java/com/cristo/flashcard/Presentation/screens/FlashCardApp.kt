package com.cristo.flashcard.Presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cristo.flashcard.Presentation.viewmodels.FlashcardVM
import com.cristo.flashcard.Presentation.viewmodels.screen

@Composable
fun FlashcardApp(viewModel: FlashcardVM) {
    val estado by viewModel.uiState.collectAsState()

    BackHandler(enabled = estado.pantalla != screen.LISTA) {
        viewModel.irA(screen.LISTA)
    }

    when (estado.pantalla) {
        screen.LISTA -> ListaScreen(
            estado = estado,
            onEstudiar = { viewModel.irA(screen.ESTUDIO) },
            onNueva = { viewModel.irA(screen.NUEVA) },
            onEliminar = { id -> viewModel.eliminarTarjeta(id) }
        )
        screen.ESTUDIO -> EstudioScreen(
            estado = estado,
            onVoltear = { viewModel.voltear() },
            onAnterior = { viewModel.anterior() },
            onSiguiente = { viewModel.siguiente() },
            onVolver = { viewModel.irA(screen.LISTA) }
        )
        screen.NUEVA -> NuevaTarjetaScreen(
            mostrarError = estado.mostrarError,
            onGuardar = { pregunta, respuesta -> viewModel.guardarTarjeta(pregunta, respuesta) },
            onCancelar = { viewModel.irA(screen.LISTA) }
        )
    }
}