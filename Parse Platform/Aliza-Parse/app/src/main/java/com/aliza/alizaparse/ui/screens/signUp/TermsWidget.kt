package com.aliza.alizaparse.ui.screens.signUp

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.aliza.alizaparse.R

@Composable
fun TermsWidget(
    modifier: Modifier,
    isChecked: Boolean, onChecked: (Boolean) -> Unit,
) {

    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onChecked,
        )
        val clickablePart = "Terms & Conditions"

        val annotatedString = buildAnnotatedString {
            append("I agree to the ")

            // استایل دادن به بخش کلیک‌پذیر متن
            pushStringAnnotation(tag = "clickable", annotation = clickablePart)
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(clickablePart)
            }
            pop()
            append(".")
        }

        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(
                    tag = "clickable",
                    start = offset,
                    end = offset
                )
                    .firstOrNull()?.let { annotation ->
                        // عملکرد کلیک برای بخش کلیک‌پذیر
                        Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show()
                    }
            },
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.dosis)),
                color = Color.Gray
            ),
            modifier = Modifier.padding()
        )
    }
}