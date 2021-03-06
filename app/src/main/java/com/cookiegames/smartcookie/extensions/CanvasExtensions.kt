package com.cookiegames.smartcookie.extensions

import com.cookiegames.smartcookie.utils.Utils.mixTwoColors
import android.graphics.*
import kotlin.math.round
import kotlin.math.tan

/**
 * Draws the trapezoid background for the horizontal tabs on a canvas object using the specified
 * color.
 *
 * @param backgroundColor the color to use to draw the tab
 * @param withShadow true if the trapezoid should have a shadow, false otherwise.
 */
fun Canvas.drawTrapezoid(backgroundColor: Int, withShadow: Boolean) {

    val shadowColor = mixTwoColors(Color.BLACK, backgroundColor, 0.5f)

    val paint = Paint().apply {
        color = backgroundColor
        style = Paint.Style.FILL
        // isFilterBitmap = true
        isAntiAlias = true
        isDither = true
        //strokeJoin = Paint.Join.ROUND
        //pathEffect = CornerPathEffect(10f)
        if (withShadow) {
            shader = LinearGradient(
                0f, 0.9f * height, 0f, height.toFloat(),
                backgroundColor, shadowColor,
                Shader.TileMode.CLAMP
            )
        }
    }

    val roundPaint = Paint().apply {
        color = backgroundColor
        style = Paint.Style.FILL
        // isFilterBitmap = true
        isAntiAlias = true
        isDither = true
        strokeJoin = Paint.Join.ROUND
        pathEffect = CornerPathEffect(10f)
        if (withShadow) {
            shader = LinearGradient(
                    0f, 0.9f * height, 0f, height.toFloat(),
                    backgroundColor, shadowColor,
                    Shader.TileMode.CLAMP
            )
        }
    }

    val radians = Math.PI / 2.75
    val base = (height / tan(radians)).toInt()

    val wallPath = Path().apply {
        reset()
        moveTo(0f, height.toFloat())
        lineTo(width.toFloat(), height.toFloat())
        lineTo((width - base).toFloat(), 4f)
        lineTo(base.toFloat(), 4f)
        close()
    }

    val roundPath = Path().apply {
        reset()
        moveTo(0f, height.toFloat())
        lineTo(width.toFloat(), height.toFloat())
        lineTo((width - base).toFloat(), 0f)
        lineTo(base.toFloat(), 0f)
        close()
    }

    drawPath(wallPath, paint)
    drawPath(roundPath, roundPaint)
}
