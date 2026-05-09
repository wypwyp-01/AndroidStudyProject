package com.wyp.studyproject

import org.junit.Test

class TestJicheng {
    @Test
    fun main() {

        val cat1 = Cat()
        val animal = cat1 as Animal
        cat1.sayHello()
        animal.sayHello()
        println(cat1.name)
        println(animal.name)
    }
}


open class Animal() {
    open val name: String = "animal"
    open fun sayHello() {
        println("你好，我是animal")
    }

}



class Cat: Animal() {
    override val name: String = "cat"

    override fun sayHello() {
        println("喵，我是猫")
    }
}