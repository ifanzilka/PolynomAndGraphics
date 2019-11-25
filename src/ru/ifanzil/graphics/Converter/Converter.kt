import ru.ifanzil.graphics.Converter.CartesianScreenPlane


object Converter {
    fun xCrt2Scr(x: Double, plane: CartesianScreenPlane): Int {
        val kw = plane.realWidth / (plane.xMax - plane.xMin)
        return (x * kw + (-plane.xMin) * kw).toInt()
    }

    fun xScr2Crt(x: Int, plane: CartesianScreenPlane): Double {
        val kw = plane.realWidth / (plane.xMax - plane.xMin)
        return x*(1.0/kw)+plane.xMin
    }

    fun yCrt2Scr(y: Double, plane: CartesianScreenPlane): Int {
        val kh=plane.realHeight / (plane.yMax - plane.yMin);
        return (kh*(plane.yMax-y)).toInt()
    }

    fun yScr2Crt(y: Int, plane: CartesianScreenPlane): Double {
        val kh=plane.realHeight / (plane.yMax - plane.yMin);
        return (-y)*(1.0/kh) + plane.yMax;
    }
}