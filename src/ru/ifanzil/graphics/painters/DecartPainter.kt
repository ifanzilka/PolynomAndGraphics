package ru.ifanzil.graphics.painters
import ru.ifanzil.graphics.Converter.CartesianScreenPlane
import java.awt.Color
import java.awt.Graphics

class DecartPainter(var plane: CartesianScreenPlane){

    init {

    }

    private fun PaintAxes(g:Graphics){//оси
        val x0 = Converter.xCrt2Scr(0.0, plane)
        val y0 = Converter.yCrt2Scr(0.0, plane)
        g.color = Color.BLACK
        g.drawLine(0, y0, plane.realWidth, y0)
        g.drawLine(x0, 0, x0, plane.realHeight)

    }
    private fun PaintTicks(g:Graphics){
        val x0 = Converter.xCrt2Scr(0.0, plane)
        val y0 = Converter.yCrt2Scr(0.0, plane)
        for (i in  (plane.xMin*10).toInt()..(plane.xMax*10).toInt()) {
            g.color=Color.DARK_GRAY
            var d = 2;
            if ((i % 5)!=0) {
                d += 1;
                g.color=Color.RED
            }
            if ((i % 10)!=0) {
                d += 1;
                g.color=Color.DARK_GRAY
            }
            val x=Converter.xCrt2Scr((i/10.0),plane)
            g.drawLine(x, y0 - d, x, y0 + d);
        }
        for (i in  (plane.yMin*10).toInt()..(plane.yMax*10).toInt()) {
            g.color=Color.BLUE
            var d = 2;
            if ((i % 5)!=0) {
                d += 1;
                g.color=Color.RED
            }
            if ((i % 10)!=0) {
                d += 1;
                g.color=Color.DARK_GRAY
            }
            val y = Converter.yCrt2Scr((i / 10.0),plane)
            g.drawLine( x0-d, y, x0+d, y);
        }

        var X_Zero=Converter.xCrt2Scr(0.0,plane)
        var Y_Zero=Converter.yCrt2Scr(0.0,plane)

           // g.draw




    }
    private fun PaintLabel(g:Graphics){//числа
        val x0 = Converter.xCrt2Scr(0.0, plane)
        val y0 = Converter.yCrt2Scr(0.0, plane)
        g.color= Color.BLUE
        for (i in  (plane.xMin*10).toInt()..(plane.xMax*10).toInt()) {
            if (i % 5!=0) continue
            if (i==0) continue
            val x = Converter.xCrt2Scr(i / 10.0,plane)
            val d :Int;
            if (i>0){
                d=20
            }else d=-10
            g.drawString((i/10.0).toString(),x-8,y0+d)
        }
        for (i in  (plane.yMin*10).toInt()..(plane.yMax*10).toInt()) {
            if (i % 5!=0) continue;
            if (i==0) continue;
            val y = Converter.yCrt2Scr(i / 10.0,plane)

            val d:Int
            if (i>0){
                d=5
            }else d=-30
            g.drawString((i/10.0).toString(),x0+d,y+5)
        }
    }
    fun paint(g: Graphics) {
        PaintAxes(g)  //оси
        PaintTicks(g)  //черточки
        //
        PaintLabel(g)//числа
    }

}