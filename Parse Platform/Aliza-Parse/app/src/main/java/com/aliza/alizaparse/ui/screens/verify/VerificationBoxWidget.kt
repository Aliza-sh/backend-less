package com.aliza.alizaparse.ui.screens.verify

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliza.alizaparse.R
import kotlinx.coroutines.delay

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun VerificationBoxWidget(
    modifier: Modifier = Modifier,
    loading: Boolean,
    otpCount: Int = 4,
    otpValue: String, onOtpValueChange: (String, Boolean) -> Unit
) {

    val rectOffsetY = remember { Animatable(1000f) }
    LaunchedEffect(Unit) {
        delay(500)

        rectOffsetY.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )

    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = rectOffsetY.value.dp)
            .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 52.dp)
        ) {
            Text(
                text = "Verification Code",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.dosis)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.primary
                ),
            )
            Text(
                text = "Please Enter Verification Code",
            )
        }

        VerificationCodeInput(
            modifier = Modifier.align(Alignment.Center),
            otpCount = otpCount,
            otpText = otpValue,
            onOtpValueChange = onOtpValueChange
        )

        TimerButton(
            login = loading,
            modifier = Modifier
                .padding(bottom = 80.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
                .height(50.dp),
        ) {
            // resending otp
        }
    }
}