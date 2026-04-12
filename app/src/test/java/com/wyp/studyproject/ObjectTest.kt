package com.wyp.studyproject

import org.junit.Test

class ObjectTest {
    @Test
    fun main() {
        val s1 = Student("w")
        val s2 = Student("w")
        println(s1 == s2)
        println(s1.hashCode())
        println(s1.toString())
        // testNull()
        // testExtends()
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
//        val s1 = Student("q",19)
//        val (a,b,c,d) = s1
//        println("a = $a,b = $b,c = $c,d = $d")
//
//
//        val u1 = UserInfo("w",12,true)
//        val (name,age) = u1
//        println("name = $name,age = $age")
//
//        val (n,_,g) = u1
//        println("n = $n,g = $g")
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

fun testExtends() {
    val stu = Student()
    val comStu = ComputerStudent("w")
    val matStu = MaterialStudent()
    stu.study()
    comStu.study()
    matStu.study()
    println("stu:${stu.project},comStu:${comStu.project},matStu:${matStu.project}")
    val student: Student = ComputerStudent("wy")
    student.study()
    type(student)
}

fun type(s: Student) {
    if (s is ComputerStudent) {
        s.coding()
    } else if (s is MaterialStudent) {
        s.experiment()
    }
    val ms = s as ComputerStudent
    ms.coding()
}







open class Student(var name: String = "",var age: Int = 0): Any() {
    constructor(name: String,grade: Float): this(name,0) {
        this.grade = grade
        // println("次要构造函数")
    }
    open var project = ""
    var isAdult: Boolean = false
    var grade: Float = 1.0f
    lateinit var address: String
    init {
        println("父类初始化")
        isAdult = if (age >= 18) true else false
    }

    init {
        // println("init2")
    }

    open fun study() {
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

    override fun equals(other: Any?): Boolean {
//        遵循等价性五大规则：
//        自反性（自己 = 自己）
//        对称性（A=B → B=A）
//        传递性（A=B、B=C → A=C）
//        一致性（多次调用结果不变）
//        非空性（x.equals (null) = false）
        if(this === other) return true  //如果引用的是同一个对象，肯定是true不多逼逼
        if(other !is Student) return false //如果要判断的对象根本不是Student类型的，那也不用继续了
        if(name != other.name) return false  //判断名字是否相同
        if(age != other.age) return false  //判断年龄是否相同
        return true   //都没问题，那就是相等了
    }

    override fun hashCode(): Int {
        var result = 1
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "Student(name='$name', age=$age, project='$project', isAdult=$isAdult, grade=$grade, address='$address')"
    }

}

class ComputerStudent: Student {
    constructor(name: String):super(name,18)

    override var project = "computer"

    override fun study() {
        super.study()
        println("I am studying computer")
    }
    fun coding() {
        println("I am studying coding")
    }
}

class MaterialStudent: Student() {
    init {
        project = "material"
    }
    override fun study() {
        println("I am studying material")
    }
    fun experiment() {
        println("I am studying experiment")
    }
}










data class UserInfo(
    val name: String = "",
    val age: Int = 0,
    val gender: Boolean = false
)























