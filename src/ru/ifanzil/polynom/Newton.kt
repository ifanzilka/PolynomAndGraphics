package ru.fanzil.polynom

import java.util.*
import kotlin.collections.ArrayList

class Newton( var valx: ArrayList<Double>, var valy: ArrayList<Double>):Polynom() {
    var termW=Polynom(arrayOf(1.0))
    var termsF=Hashtable<Pair<Int,Int>,Double>()
    init{
        createNewton()
    }

    constructor(): this(ArrayList(), ArrayList())

    constructor(x: Array<Double>, y: Array<Double>): this(ArrayList(), ArrayList()){
        val min = if (x.size<=y.size) x else y
        min.indices.forEach {
            if(!valx.contains(x[it])) {
                valx.add(x[it])
                valy.add(y[it])
            }
        }
        createNewton()
    }

    constructor(vals:MutableMap<Double,Double>): this(ArrayList(), ArrayList()){
        vals.keys.forEach {
            valx.add(it)
            valy.add(vals.getValue(it))
        }
        createNewton()
    }
    private fun createNewton() {
        if(valx.size>0)
        {
            var res=Polynom()
            res+=termW*getTermF(0,0)
            for (key in 1..(valx.size-1))
            {
                termW*=Polynom(arrayOf(-valx[key-1],1.0))
                res+=termW*getTermF(0,key)
            }
            c.clear()
            c.addAll(res.c)
        }
    }

    private fun getTermF(begin:Int,end:Int): Double { //Получаем f(x1,x_k)
        if (begin == end) {
            termsF[Pair(begin,begin)]=valy[begin]
            return valy[begin]
        } else if (end == begin + 1) {
            termsF[Pair(begin,end)]=(valy[end] - valy[begin]) / (valx[end] - valx[begin])
            return termsF.getValue(Pair(begin,end))
        } else {
            if(termsF.keys.contains(Pair(begin,end)))return termsF.getValue(Pair(begin,end))
            else {
                termsF[Pair(begin,end)]=(getTermF(begin + 1, end) - getTermF(begin, end - 1)) / (valx[end] - valx[begin])
                return termsF.getValue(Pair(begin,end))
            }
        }
    }
    fun addNode(x: Double, y: Double) {//добавили узел
        if(!valx.contains(x)) {
            valx.add(x)
            valy.add(y)

            var res = Polynom(c)
            if (valx.size>=2){
                termW*=Polynom(arrayOf(-valx[valx.size-2],1.0))}
            res += termW * getTermF(0, valx.size - 1)
            c.clear()
            c.addAll(res.c)
        }
    }
    fun clear(){
        valx.clear()
        valy.clear()
        termW=Polynom(arrayOf(1.0))
        termsF.clear()
        c.clear()
        c.add(0.0)
    }
}

