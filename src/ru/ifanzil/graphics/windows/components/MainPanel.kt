package ru.ifanzil.graphics.windows.components


import ru.ifanzil.graphics.painters.DecartPainter
import ru.ifanzil.graphics.painters.NewtonPainter
import java.awt.Graphics
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JPanel

class MainPanel(var painter: NewtonPainter, var DPainter: DecartPainter):JPanel() {
    init{
        addComponentListener(
           object:ComponentAdapter(){
               override fun componentResized(e: ComponentEvent?) {//если изменили размер
                 repaint()
               }
           }
        )

    }

    override fun paint(g: Graphics?) {
        DPainter.plane.realWidth = width
        DPainter.plane.realHeight = height
        super.paint(g)
        if (g!=null) {
            DPainter.paint(g)
            painter.paint(g)
        }
    }
}