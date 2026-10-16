package com.cristo.flashcard.Presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import com.cristo.flashcard.R

@Composable
fun brushDegradado(): Brush {
    return Brush.horizontalGradient(
        colors = listOf(
            colorResource(R.color.primario),
            colorResource(R.color.violeta)
        )
    )
}