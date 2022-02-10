package my.bulhakov.loveyou

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

class DrawView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var framesPerSecond = 60
    var startTime: Long = System.currentTimeMillis()

    private var canvasCenterWidth: Int = 0
    private var canvasCenterHeight: Int = 0
    var i = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        doDrawLine(canvas)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        canvasCenterWidth = width / 2
        canvasCenterHeight = height / 2
    }

    private fun doDrawLine(canvas: Canvas?) {
        val elapsedTime = System.currentTimeMillis() - startTime
        drawLine(i++, canvas)
        if(i < width)
            this.postInvalidateDelayed(( 1000 / framesPerSecond).toLong());
    }

    private fun drawLine(i: Int, canvas: Canvas?) {
        for(t in 0..i) {
            val p = Paint()
            p.setARGB(255, 255, (t - 230) % 255, (t - 230) % 255)
            val x = getXCoordinate(t)
            val y = getYCoordinate(t)
            canvas?.drawLines(
                floatArrayOf(x, y, canvasCenterWidth.toFloat(), canvasCenterHeight.toFloat()), p
            )
        }
    }

    private fun getXCoordinate(i: Int) = (canvasCenterWidth + (xt(i.toDouble()) * 20)).toFloat()

    private fun getYCoordinate(i: Int) = (canvasCenterHeight - (yt(i.toDouble()) * 20)).toFloat()

    private fun xt(t: Double) = 16 * (sin(t).pow(3.0))

    private fun yt(t: Double) = (13 * cos(t)) - (5 * cos(2 * t)) - 2 * cos(3 * t) - cos(4 * t)


}