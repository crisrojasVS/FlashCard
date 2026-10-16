package com.cristo.flashcard.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.cristo.flashcard.Presentation.viewmodels.FlashcardVM
import com.cristo.flashcard.data.FlashcardDataSource
import com.cristo.flashcard.data.FlashcardRepository

class AppContainer(context: Context) {

    private val dataSource = FlashcardDataSource(context.applicationContext)
    private val repository = FlashcardRepository(dataSource)

    val factory: ViewModelProvider.Factory = viewModelFactory {
        initializer { FlashcardVM(repository) }
    }
}