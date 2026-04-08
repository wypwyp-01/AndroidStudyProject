package com.wyp.studyproject

import org.junit.Test

class ObjectTest {
    @Test
    fun main() {
        testNull()
//        val s = Student("w",18)
//        s.address = "tianjin"
//        println("name = ${s.name},age = ${s.age},grade = ${s.grade},address = ${s.address},isAdult = ${s.isAdult}")
//        val s2 = Student("w",26)
////        s2.age = 28
//        s2.address = "tianjin"
//        println("name = ${s.name},age = ${s.age},grade = ${s.grade},address = ${s.address},isAdult = ${s.isAdult}")
//        println(s === s2)
//        val s3 = Student("y",15.0f)
//        s3.address = "tianjin"
//        println("name = ${s3.name},age = ${s3.age},grade = ${s3.grade},address = ${s3.address},isAdult = ${s3.isAdult}")
//        s3.study()
//        println(s3.hashCode())
//        println(s3.toString())
//        s3 say1 "nihao"
        // 解构声明
        val s1 = Student("q",19)
        val (a,b,c,d) = s1
        println("a = $a,b = $b,c = $c,d = $d")


        val u1 = UserInfo("w",12,true)
        val (name,age) = u1
        println("name = $name,age = $age")

        val (n,_,g) = u1
        println("n = $n,g = $g")
    }
}

fun testNull() {
    // 可空类型
    var a: Student? = null
    a = Student("y",18)

    println(a!!.name) // 非空断言
    println(a?.name) // 安全调用运算符
    // Elvis运算符
    val name = a?.name ?: "空"
    a = null
    val name1 = a?.name ?: "空"
    println("name = $name,name1 = $name1")
}



class Student(val name: String,var age: Int) {
    constructor(name: String,grade: Float): this(name,0) {
        this.grade = grade
        println("次要构造函数")
    }
    var isAdult: Boolean = false
    var grade: Float = 1.0f
    lateinit var address: String
    init {
        println("init1,name = $name")
        isAdult = if (age >= 18) true else false
    }

    init {
        println("init2")
    }

    fun study() {
        println("I am studying")
        grade += 10
        println("${grade}")
    }


    infix fun say(action: String) {
        println("${name} say action")
    }
    infix fun say1(action: String) {
        println("${name} say $action")
    }

    operator fun component1() = name
    operator fun component2() = age
    operator fun component3() = isAdult
    operator fun component4() = grade
}



infix fun Student.say(action: String) {
    println("")
}

data class UserInfo(
    val name: String = "",
    val age: Int = 0,
    val gender: Boolean = false
)























