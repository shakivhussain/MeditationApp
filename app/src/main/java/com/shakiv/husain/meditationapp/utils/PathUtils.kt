package com.shakiv.husain.meditationapp.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs


fun Path.standardQuadFromTo(from: Offset, to: Offset) {


    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.y) / 2F,
        abs(from.y + to.x) / 2F,
    )

}