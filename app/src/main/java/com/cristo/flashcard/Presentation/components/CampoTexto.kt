package com.cristo.flashcard.Presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cristo.flashcard.R

@Composable
fun CampoTexto(
    etiqueta: String,
    valor: String,
    onCambio: (String) -> Unit,
    lineas: Int = 1
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = etiqueta,
            color = colorResource(R.color.texto),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        OutlinedTextField(
            value = valor,
            onValueChange = onCambio,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            minLines = lineas,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.primario),
                unfocusedBorderColor = colorResource(R.color.borde),
                focusedContainerColor = colorResource(R.color.blanco),
                unfocusedContainerColor = colorResource(R.color.fondo)
            )
        )
    }
}