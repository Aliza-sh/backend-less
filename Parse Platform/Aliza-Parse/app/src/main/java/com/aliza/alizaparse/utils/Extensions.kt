package com.aliza.alizaparse.utils

import android.net.Uri
import android.util.Patterns
import java.io.File

fun ClosedFloatingPointRange<Float>.random(): Float {
    return (start + (endInclusive - start) * kotlin.random.Random.nextFloat())
}


fun String.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.length >= 6
}