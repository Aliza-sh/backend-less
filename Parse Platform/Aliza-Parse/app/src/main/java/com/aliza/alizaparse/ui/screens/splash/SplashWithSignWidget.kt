package com.aliza.alizaparse.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliza.alizaparse.R
import com.aliza.alizaparse.ui.component.NightSky
import com.aliza.alizaparse.ui.theme.Green
import com.aliza.alizaparse.utils.SIGN_UP_SCREEN
import com.aliza.alizaparse.utils.SIGN_IN_SCREEN
import kotlinx.coroutines.delay

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun SplashWithSignWidget(
    modifier: Modifier = Modifier,
    onNavigateTo: (String) -> Unit
) {
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val rectOffsetY = remember { Animatable(-500f) } // مستطیل از بیرون صفحه شروع می‌شود
    val button1OffsetX = remember { Animatable(500f) } // دکمه 1 از سمت راست بیرون شروع می‌شود
    val button2OffsetX = remember { Animatable(500f) } // دکمه 2 از سمت راست بیرون شروع می‌شود

    // انیمیشن‌ها
    LaunchedEffect(Unit) {
        // انیمیشن برای مقیاس لوگو
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500, easing = LinearEasing)
        )

        delay(500) // تاخیر قبل از اجرای انیمیشن مستطیل

        // انیمیشن برای حرکت مستطیل
        rectOffsetY.animateTo(
            targetValue = 0f, // مستطیل وارد صفحه می‌شود
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )

        // همزمان لوگو و دایره بالا بروند
        offsetY.animateTo(
            targetValue = -80f, // لوگو به بالا حرکت می‌کند
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )

        // بعد از کامل شدن انیمیشن‌های بالا، دکمه‌ها از سمت راست وارد شوند
        delay(500) // کمی تاخیر قبل از انیمیشن دکمه‌ها
        button1OffsetX.animateTo(
            targetValue = 0f, // دکمه 1 به وسط صفحه می‌آید
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
        button2OffsetX.animateTo(
            targetValue = 0f, // دکمه 2 به وسط صفحه می‌آید
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // مستطیل با گوشه‌های گرد که بعد از انیمیشن لوگو وارد می‌شود
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp) // ارتفاع مستطیل (یک سوم صفحه)
                .offset(y = rectOffsetY.value.dp) // حرکت مستطیل از بیرون به داخل
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp))
        ) { NightSky() }

        // دایره به عنوان بک‌گراند
        Box(
            modifier = Modifier
                .size(160.dp * scale.value) // اندازه دایره تغییر می‌کند
                .align(Alignment.Center)
                .offset(y = offsetY.value.dp) // حرکت دایره به سمت بالا
                .background(Color.White, shape = CircleShape)
        )

        // لوگو
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.Center)
                .offset(y = offsetY.value.dp) // حرکت لوگو همزمان با دایره به سمت بالا
                .alpha(alpha.value)
                .background(Color.Gray.copy(0.1f), shape = CircleShape)
                .padding(8.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onNavigateTo.invoke(SIGN_UP_SCREEN) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .offset(x = button1OffsetX.value.dp, y = (-60).dp) // حرکت از سمت راست به وسط
            ) {
                Text(
                    text = "SIGN UP",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.dosis)),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                )
            }

            Text(
                text = "Already a member?",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.dosis)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.offset(x = button2OffsetX.value.dp, y = (-35).dp)
            )

            TextButton(
                onClick = { onNavigateTo.invoke(SIGN_IN_SCREEN) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .offset(x = button2OffsetX.value.dp, y = (-40).dp)
            ) {
                Text(
                    text = "Sing in",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.dosis)),
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        color = Green
                    )
                )
            }
        }
    }
}