package com.wyp.studyproject

import android.util.Log
import okio.ArrayIndexOutOfBoundsException
import org.junit.Test
import org.junit.experimental.theories.suppliers.TestedOn

class ExceptionTest {
    @Test
    fun main() {
        // throw Exception("test exception")
        div(2,0)

    }
}







fun div(a : Int , b: Int): Int {
    try {
        val ret = a / b
        println("ret = $ret")
        return ret
    } catch (e: ArrayIndexOutOfBoundsException) {
        e.printStackTrace()
    } catch (e: ArithmeticException) {

    } finally {
        println("执行完成")
    }
    println("hello")
    return 0
}

class MyException(message: String): Exception(message)