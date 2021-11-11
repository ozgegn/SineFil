package com.ozgegn.sinefil.customView

import android.animation.AnimatorSet
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View

class CustomProgressView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val animatorSet: AnimatorSet = AnimatorSet()
    private val dots = arrayListOf<Dot>()
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    val radius = 150
    val radiuses = floatArrayOf(0f, 30f, 60f)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            paint.color = Color.argb(0.5f, 0.8f, 0.7f, 0f)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animatorSet.cancel()
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == VISIBLE) {
            animatorSet.start()
        } else {
            animatorSet.cancel()
            dots.forEach {
                it.reset()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        dots.forEach { it.draw(canvas, paint) }
        for (i in radiuses.indices) {
            val cr = radiuses[i]
            canvas?.drawCircle(radius.toFloat(), radius.toFloat(), cr, paint)
            radiuses[i] = incrementRadius(cr)
        }
        postDelayed({
            invalidate()
        }, FRAME_TIME)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(2 * radius, 2 * radius)
    }

    private fun incrementRadius(radius: Float): Float {
        return if (radius < 150) {
            (radius + 3)
        } else {
            0f
        }
    }

}

class Dot(
    var x: Float,
    var y: Float,
    var radius: Float
) {

    private val startX: Float = x
    private val startY: Float = y

    fun draw(canvas: Canvas?, paint: Paint) {
        canvas?.drawCircle(x, y, radius, paint)
    }

    fun reset() {
        x = startX
        y = startY
    }
}

const val FRAME_TIME = (2000 / 60).toLong()
