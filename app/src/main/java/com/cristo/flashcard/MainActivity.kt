package com.cristo.flashcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.cristo.flashcard.Presentation.screens.FlashcardApp
import com.cristo.flashcard.Presentation.viewmodels.FlashcardVM
import com.cristo.flashcard.di.AppContainer
import com.cristo.flashcard.ui.theme.FlashCardTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val viewModel: FlashcardVM by viewModels {
        AppContainer(applicationContext).factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlashCardTheme {
                FlashcardApp(viewModel)
            }
        }
    }
}