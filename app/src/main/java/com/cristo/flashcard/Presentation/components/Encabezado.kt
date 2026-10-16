package com.cristo.flashcard.Presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cristo.flashcard.R
import com.cristo.flashcard.Presentation.components.brushDegradado

@Composable
fun Encabezado(
    titulo: String,
    subtitulo: String,
    contenido: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = brushDegradado(),
                shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
            )
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 28.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = titulo,
            color = colorResource(R.color.blanco),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitulo,
            color = colorResource(R.color.subtitulo_encabezado),
            fontSize = 14.sp
        )
        contenido()
    }
}