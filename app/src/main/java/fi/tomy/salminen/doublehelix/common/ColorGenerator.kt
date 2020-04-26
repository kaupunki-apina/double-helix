package fi.tomy.salminen.doublehelix.common

import android.graphics.Color


fun GetColor(seed: String): Int {
    val sum = seed.fold(0) { acc, character ->
        acc + character.toInt()
    }

    // Use the whole hue spectrum
    val h = ("0." + Math.sin(sum.toDouble() + 1).toString().substring(6)).toDouble() * 360
    // Limit saturation to 0.5 - 1
    val s = ("0." + Math.sin(sum.toDouble() + 2).toString().substring(6)).toDouble() * 0.5 + 0.5
    // Limit brightness to 0.8 - 1
    val v = ("0." + Math.sin(sum.toDouble() + 3).toString().substring(6)).toDouble() * 0.2 + 0.8

    return Color.HSVToColor(floatArrayOf(h.toFloat(), s.toFloat(), v.toFloat()))
}