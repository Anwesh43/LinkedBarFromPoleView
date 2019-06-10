package com.anwesh.uiprojects.barfrompoleview

/**
 * Created by anweshmishra on 11/06/19.
 */

import android.app.Activity
import android.graphics.Color
import android.graphics.RectF
import android.graphics.Paint
import android.graphics.Canvas
import android.content.Context
import android.view.View
import android.view.MotionEvent

val nodes : Int = 5
val lines : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#4CAF50")
val backColor : Int = Color.parseColor("#BDBDBD")
val hFactor : Float = 4f
val rotDeg : Float = 90f

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawBarFromPole(i : Int, sc : Float, size : Float, paint : Paint) {
    val x : Float = -size + size * i
    val h : Float = size / hFactor
    val sf : Float = 1f - 2 * i
    val y : Float = (size - h) * sf * sc
    save()
    translate(x, y)
    drawRect(RectF(0f, -h, size, h), paint)
    restore()
    drawLine(0f, 0f, 0f, y, paint)
}

fun Canvas.drawBFPNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    save()
    translate(w / 2, gap * (i + 1))
    rotate(rotDeg * sc2)
    for (j in 0..(lines - 1)) {
        drawBarFromPole(j, sc1.divideScale(j, lines), size, paint)
    }
    restore()
}
