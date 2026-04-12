package com.wyp.studyproject

import org.junit.Test

class FanxingTest {
    @Test

    fun main() {
        var obj = object: Person {
            fun test() {

            }
            val name: String = "aaa"
            override fun onClick() {

            }
        }
        obj.onClick()

        val util1 = MyUtil
        val util2 = MyUtil
        util1.number += 10
        println(MyUtil.number)
        util1.weatherUtil()
        println(util2.number)




    }



    fun specialClass() {
//        val s1 = MyScore<Int>("wyp","233",99)
//        println(s1.value)
//        s1.toString()
//        val s2 = MyScore<String>("wyp","233","优秀")
//        println(s2.value)
//        s2.toString()
        // testApply()
        // testWith()
//        val u = User("123","123")
//        println(u.toString())
        val state = State.SUCCESS
        println(state.name)
        println(state.ordinal)
        println(state.initialState)
        println(state.number)
        state.number = 100
        println(state.number)
        println(state.isLoading())
        when(state) {
            State.SUCCESS->{}
            State.LOADING->{}
            State.FAIL->{}
        }
        val state1 = State.valueOf("SUCCESS")
        println(state1)
        println(State.entries)

    }



    enum class State(var initialState: String, var number: Int) {
        SUCCESS("success",2),
        FAIL("fai",3),
        LOADING("loading",4);
        fun isLoading(): Boolean {return this == LOADING}
    }








    data class User(
        var name: String,
        var id: String
    ) {
        val temp: Int = 0
    }


















    fun testApply() {
        val s = Student("www",19).apply {
            grade = 99.0f
            project = "computer"
            address = "tianjin"
        }
        println(s.toString())
    }

    fun testWith() {
        val s: Student? = null
        with(s) {
            println(this?.toString())
        }

        val str: String? = "hello"

// 长度 > 3 才继续处理
        val uppers = str?.takeIf { it.length > 3 }
            ?.uppercase() ?: str


    }









}



class MyScore<T>(var name: String, var id: String, var value:T) {
    val v: Any? = value

    override fun toString(): String {
        val str = "Score(name = $name,id = $id,value = $value)"
        println(str)
        return str
    }
}




fun <T> test(t: T): T {
    return t
}

interface Person {
    fun onClick()
}


object MyUtil{
    var number: Int = 0
    init {
        println("first init")
    }
    fun weatherUtil() {
        println("weatherUtil")
    }
}









