package com.cristo.flashcard.Presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.cristo.flashcard.R
import androidx.compose.ui.unit.dp

@Composable
fun DialogoEliminar(
    pregunta: String,
    onConfirmar: () -> Unit,
    onCancelar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text(text = stringResource(R.string.titulo_eliminar)) },
        text = { Text(text = stringResource(R.string.mensaje_eliminar, pregunta)) },
        confirmButton = {
            TextButton(onClick = onConfirmar) {
                Text(
                    text = stringResource(R.string.boton_eliminar),
                    color = colorResource(R.color.error),
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text(
                    text = stringResource(R.string.boton_cancelar),
                    color = colorResource(R.color.texto_suave)
                )
            }
        },
        containerColor = colorResource(R.color.blanco),
        shape = RoundedCornerShape(24.dp)
    )
}