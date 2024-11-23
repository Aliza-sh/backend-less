package com.aliza.alizaparse.ui.screens.verify

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerificationCodeInput(
    modifier: Modifier = Modifier,
    otpCount: Int = 4,
    otpText: String,
    boxSize: Dp = 48.dp,
    boxActiveBackground: Color = MaterialTheme.colorScheme.primary,
    boxFilledBackground: Color = Color.Gray.copy(alpha = 0.1f),
    boxActiveBorderColor: Color = MaterialTheme.colorScheme.primary,
    otpTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    otpTextStyle: TextStyle = MaterialTheme.typography.headlineLarge,
    onOtpValueChange: (String, Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    BasicTextField(
        modifier = modifier,
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpValueChange.invoke(it.text, it.text.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    val isFocused = otpText.length == index
                    val char = when {
                        index == otpText.length -> "0"
                        index > otpText.length -> ""
                        else -> otpText[index].toString()
                    }
                    Text(
                        modifier = Modifier
                            .width(boxSize)
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp, when {
                                    isFocused -> boxActiveBorderColor
                                    else -> Color.Transparent
                                },RoundedCornerShape(8.dp)
                            )
                            .background(
                                when {
                                    index == otpText.length -> boxFilledBackground
                                    index > otpText.length -> boxFilledBackground
                                    else -> boxActiveBackground
                                }
                            )
                            .padding(vertical = 8.dp),
                        text = char,
                        style = otpTextStyle,
                        color = if (isFocused) {
                            boxActiveBorderColor
                        } else {
                            otpTextColor
                        },
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}
