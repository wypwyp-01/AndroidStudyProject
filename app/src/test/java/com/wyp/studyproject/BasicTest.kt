package com.wyp.studyproject

import org.junit.Test

class BasicTest {
    @Test
    fun testType() {
        var a: Byte = 1
        val c = a++
        println(c)
        val d = false

        val ch = 'a'
        println(ch)
        val ch1 = 1.toChar()
        println(ch1)
        println(ch.code)
        println('王'.code)
    }

    @Test
    fun testString() {
        val str = """
            hahaha
            jdidij  fjkhvfk 
            khnkhlknkjhn
        """.trimIndent()
        println(str)
    }

}