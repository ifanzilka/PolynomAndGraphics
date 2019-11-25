package ru.avk.graphics.windows

import java.awt.Color
import java.awt.Dimension
import javax.swing.*
import ru.fanzil.polynom.Newton
import ru.ifanzil.graphics.Converter.CartesianScreenPlane
import ru.ifanzil.graphics.painters.DecartPainter
import ru.ifanzil.graphics.painters.NewtonPainter
import ru.ifanzil.graphics.windows.components.MainPanel
import java.awt.Component
import javax.swing.JSpinner
import java.awt.event.*


class Window: JFrame() {
    private var mainPanel: MainPanel
    private val controlPanel: JPanel
    private val btnClear: JButton
    private val cbShowDerivative: JCheckBox
    private var xMin: JSpinner
    private var xMax: JSpinner
    private var yMin: JSpinner
    private var yMax: JSpinner
    private val lxMin: JLabel
    private val lxMax: JLabel
    private val lyMin: JLabel
    private val lyMax: JLabel

    private val dim: Dimension

    init {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        dim = Dimension(700, 700)
        minimumSize = dim

        val plane = CartesianScreenPlane(
            -1,
            -1,
            -5.0,
            5.0,
            -5.0,
            5.0
        )

        var pNewton= Newton()//класс ньютог
        var pNewtonPainter= NewtonPainter(plane, pNewton)//класс который рисует полином .plane- размеры оси


        mainPanel = MainPanel(pNewtonPainter, DecartPainter(plane))
        mainPanel.background = Color.WHITE
        controlPanel = JPanel()
        controlPanel.border =
            BorderFactory.createTitledBorder(
                "Параметры плоскости"
            )
        btnClear = JButton("Очистить")
        cbShowDerivative = JCheckBox("Показывать производную",false)
        lxMin = JLabel("xMin =")
        lxMax = JLabel("xMax =")
        lyMin = JLabel("yMin =")
        lyMax = JLabel("yMax =")



        xMin = JSpinner(SpinnerNumberModel(-5.0,-100.0,100.0,0.1))
        xMax = JSpinner(SpinnerNumberModel(5.0,-100.0,100.0,0.1))
        yMin = JSpinner(SpinnerNumberModel(-5.0,-100.0,100.0,0.1))
        yMax = JSpinner(SpinnerNumberModel(5.0,-100.0,100.0,0.1))


        val gl = GroupLayout(contentPane)
        layout = gl
        gl.setVerticalGroup(
            gl.createSequentialGroup()
                .addGap(4)
                .addComponent( mainPanel,
                    (dim.height*0.6).toInt(),
                    GroupLayout.DEFAULT_SIZE,
                    GroupLayout.DEFAULT_SIZE)
                .addGap(4)
                .addComponent(controlPanel,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(4)
        )
        gl.setHorizontalGroup(
            gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                    gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(mainPanel,
                            (dim.width*0.7).toInt(),
                            GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE
                        )
                        .addComponent(controlPanel)
                )
                .addGap(4)
        )
        val gl2 = GroupLayout(controlPanel)
        controlPanel.layout = gl2
        gl2.setVerticalGroup(
            gl2.createSequentialGroup()
                .addGap(4)
                .addGroup(
                    gl2.createParallelGroup(
                        GroupLayout.Alignment.CENTER
                    )
                        .addGroup(
                            gl2.createSequentialGroup()
                                .addComponent(lxMin,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                                .addGap(4)
                                .addComponent(lyMin,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(
                            gl2.createSequentialGroup()
                                .addComponent(xMin,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                                .addGap(4)
                                .addComponent(yMin,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(
                            gl2.createSequentialGroup()
                                .addComponent(lxMax,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                                .addGap(4)
                                .addComponent(lyMax,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(
                            gl2.createSequentialGroup()
                                .addComponent(xMax,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                                .addGap(4)
                                .addComponent(yMax,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(
                            gl2.createSequentialGroup()
                                .addComponent(
                                    cbShowDerivative as Component?,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                                .addGap(4)
                                .addComponent(btnClear,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                        )

                )
                .addGap(4)
        )
        gl2.setHorizontalGroup(
            gl2.createSequentialGroup()
                .addGap(4)
                .addGroup(
                    gl2.createParallelGroup(
                        GroupLayout.Alignment.LEADING
                    )
                        .addComponent(lxMin)
                        .addComponent(lyMin)

                )
                .addGroup(
                    gl2.createParallelGroup(
                        GroupLayout.Alignment.LEADING
                    )
                        .addComponent(xMin)
                        .addComponent(yMin)

                )
                .addGap(4, 4, Int.MAX_VALUE)
                .addGroup(
                    gl2.createParallelGroup(
                        GroupLayout.Alignment.LEADING
                    )
                        .addComponent(lxMax)
                        .addComponent(lyMax)

                )
                .addGroup(
                    gl2.createParallelGroup(
                        GroupLayout.Alignment.LEADING
                    )
                        .addComponent(xMax)
                        .addComponent(yMax)

                )
                .addGap(4, 4, Int.MAX_VALUE)
                .addGroup(
                    gl2.createParallelGroup(
                        GroupLayout.Alignment.LEADING
                    )
                        .addComponent(cbShowDerivative)
                        .addComponent(btnClear)

                )
                .addGap(4)
        )
        //когда меняем
        xMin.addChangeListener(){
            plane.xMin=xMin.value.toString().toDouble()
            mainPanel.repaint()
        }
        xMax.addChangeListener(){
            plane.xMax=xMax.value.toString().toDouble()
            mainPanel.repaint()
        }
        yMin.addChangeListener(){
            plane.yMin=yMin.value.toString().toDouble()
            mainPanel.repaint()
        }
        yMax.addChangeListener() {
            plane.yMax = yMax.value.toString().toDouble()
            mainPanel.repaint()
        }
        mainPanel.addMouseListener( //нажали мышку
            object : MouseAdapter(){
                override fun mousePressed(e: MouseEvent) {
                    pNewton.addNode(Converter.xScr2Crt(e.x, plane), Converter.yScr2Crt(e.y, plane))//добавляем узел
                    mainPanel.repaint()
                }
            }
        )
        cbShowDerivative.addActionListener{
            if(cbShowDerivative.isSelected) {
                pNewtonPainter.boolPaintDeravative=true
            }else{pNewtonPainter.boolPaintDeravative=false }
            mainPanel.repaint()
        }
       btnClear.addActionListener {
           pNewton.clear()
           mainPanel.repaint()
       }
        pack()
        isVisible = true
    }
}