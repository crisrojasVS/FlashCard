package com.cristo.flashcard.Presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cristo.flashcard.Presentation.viewmodels.FlashcardVM
import com.cristo.flashcard.Presentation.viewmodels.screen

@Composable
fun FlashcardApp(viewModel: FlashcardVM) {
    val estado by viewModel.uiState.collectAsState()

    when (estado.pantalla) {
        screen.LISTA -> ListaScreen(
            estado = estado,
            onEstudiar = { viewModel.irA(screen.ESTUDIO) },
            onNueva = { viewModel.irA(screen.NUEVA) }
        )
        else -> {}
    }
}