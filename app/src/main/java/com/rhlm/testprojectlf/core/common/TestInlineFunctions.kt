package com.rhlm.testprojectlf.core.common

fun main(){
    getData { println("Hello World")
        return@getData
    }
    println("remaining data")
}

inline fun getData(crossinline func: () -> Unit){
    func.invoke()
}