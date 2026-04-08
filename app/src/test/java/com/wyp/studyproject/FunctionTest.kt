package com.wyp.studyproject

import org.junit.Test




val a : String = "aaa"
var b: Int = 0
    get(): Int {
        return field * 10
    }
    set(value) {
        field = value * 3
    }


class FunctionTest {
    @Test
    fun main() {
//
//        println(a)
//        testVal()
//        println(testDigui(5))
        testinline() {
            println(it.length)
        }
    }
    inline fun testinline(func: (s: String)-> Unit) {
        println("aaa")
        println("bbb")
        func("asdfg")
    }

    fun testDigui(n: Int): Int {
        if (n <= 0) return 0
        return n + testDigui(n - 1)
    }

    fun testVal() {
        println(a)
    }























    fun lambda() {
        // 函数类型的变量，只能有一个返回值，最后一行的表达式就是返回值
        val func: (String) -> Int = {s->
            s.length
        }

        // 函数也可以接受函数类型的变量
        val value = funTest("sum","test") {s1,s2->
            s1.length + s2.length
        }
        print("$value \n") // 7

        // 如果有一个现成的函数,可以引用他，用 ::函数名 把一个已有函数当作函数类型的变量使用。这是 Kotlin 里非常常见的写法。
        val fun1: (String) -> String = ::test1
        print("${fun1("aaBBcc")} \n") // AABBCC

        // 如果没有现成的函数，可以写一个匿名函数。写法上，匿名函数只是没有名字，其他写法和普通函数完全一样。匿名函数和普通的函数区别不大
        val fun2: (String) -> Int = fun (str:String): Int {
            return str.length
        }

        val fun3: (String) -> Int = {
            it.length
        }


        highFun( {
            // 做一些操作
            it.length
        })




    }

    fun test1(str:String): String {
        return str.uppercase()
    }
    fun funTest(a: String,b:String,sum:(c:String,d:String) -> Int): Int {
        return sum(a,b)
    }

    fun highFun(function: (String)-> Int) {
        println(function("aaa"))
    }
}












