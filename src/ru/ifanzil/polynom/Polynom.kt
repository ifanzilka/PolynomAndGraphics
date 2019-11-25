package ru.fanzil.polynom
import kotlin.math.abs
open class Polynom(){

    val c: ArrayList<Double>//доступ к оригиналу только внутри класса

    val coeffs:ArrayList<Double>//для получение массива кф
        get() {
            val cf=ArrayList<Double>()
            cf.addAll(c)
            return cf
        }


    init{//конструктор без параметров
        c = ArrayList()
        c.add(0.0)
    }


    constructor(coeffs: ArrayList<Double>): this(){ //констурктор с параметром ArrayList<Double>
        //this()-вызывается конструктор по умолчанию
        c.clear()
        c.addAll(coeffs)
        correctDeg()
    }

    constructor(coeffs: Array<Double>): this(){//констурктор с параметром Array<Double>
        //this()-вызывается конструктор по умолчанию
        c.clear()
        coeffs.forEach { c.add(it) }
        correctDeg()
    }
    constructor(degree:Int,_valueGenerator:(Int)->Double):this(){//первый параметр- степень. второй как генерируются кф
        c.clear()
        for(i in 0..degree) {
            c.add(_valueGenerator(i))
        }
    }

    fun correctDeg(){//метод который удаляет последние нули
        var i = deg
        while (i > 0 && Math.abs(c[i]) < 1e-50) {
            c.removeAt(i)
            i--
        }
    }

    val deg: Int//по умодчанию поле Public(степень)
        get() = c.size - 1

    operator fun plus(other: Polynom): Polynom {//сложение Polynom
        val (min_p, max_p) =//определяем пару
            if (deg<other.deg) Pair(this, other)
            else Pair(other, this)

        val cf = Array(max_p.deg+1) {//конструтор массива с двумя параметрами
            //1-размер массива 2-функция для кажого элемента массива
            if (it<=min_p.deg) min_p.c[it]+max_p.c[it]
        else max_p.c[it]
        }
        return Polynom(cf)

    }
    operator fun times(x:Double): Polynom =//умножение на число
        Polynom(Array(deg + 1) { c[it] * x })
    //=-результат возвраззаем обект класса // вызыввает конструктор с размером. и кажому эжементу умножаем старый кф на чичсло
  /*  operator fun times(x: Double): ru.fanzil.Polynom.Polynom {
        val cf = Array<Double>(c.size){0.0}
        for (i in cf.indices) {
            cf[i] = c[i] * x

        }
        return ru.fanzil.Polynom.Polynom(cf)
    }*/

    operator fun div(x: Double)=this*(1.0/x)

    fun GetValue(x: Double): Double {
        /*var res = c[0]
        var px = x
        for (i in 1 until c.size) {
            res += px * c[i]
            px *= x
        }
        return res*/
        var px=1.0
        return c.reduce{s,i->px*x;s+i*px}//reduce -считает сумму
        //s-сумма по умолч 0, i-кажый шаг что делаем(px*x);s+ipx-сумма
    }
    operator fun times(other: Polynom): Polynom {//умножение полиномов
        val cf = Array(deg + other.deg + 1){0.0}

        // ru.fanzil.Polynom.Polynom res = new ru.fanzil.Polynom.Polynom(deg()+other.deg());
        for (i in 0..this.deg) {
            for (j in 0..other.deg) {
                cf[i + j] += c[i] * other.c[j]
            }
        }

        return Polynom(cf)


    }
    //Производная
    fun deravative():Polynom{
        val res=Polynom()
        if (c.size>1) {
            res.c.remove(res.c[0])
            for (i in 1..c.size - 1) {
                res.c.add(c[i] * i)
            }
        }
        return res
    }
    operator fun minus(other: Polynom)=this+other*-1.0



   override fun toString(): String {
        val res = StringBuilder()

        for (i in c.indices.reversed()) {
            if (Math.abs(c[i]) > 1e-6) {
                if (c[i] < 0)
                    res.append(" - ")
                else if (c[i] > 0 && i != c.size - 1)
                    res.append(" + ")
                if (Math.abs(c[i]) - 1 > 1e-6 || i == 0)
                    res.append(Math.abs(c[i]))

                if (Math.abs(c[i]) > 1e-6 && i > 0) {
                    res.append('x')
                    if (i > 1) {
                        res.append("^")

                        res.append(i)

                    }
                }


            } else if (c.size == 1) {
                res.append("0")
            }
        }
        return res.toString()
    }

    fun getValue(x: Double): Double {
        var px = 1.0
        return c.reduce{ s, i -> px*=x; s + i * px }
    }





}