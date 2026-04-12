package com.wyp.studyproject

import org.junit.Test


class AbstractClassTest {
    @Test
    fun main() {
        println("qwertyu".uppercase())
        println(2.dp)
        val p1 = BigPeople()
        println(p1.type)
        p1.test()
        val bs1 = BigStudent()
        println(bs1.sleepTime)
        bs1.play()
    }
}

interface PlayGame {
    val playTime: Int
        get() = 1
    fun playGame()
    fun play() {
        println("play")
    }
}
interface Sleep {
    val sleepTime: Int
    fun sleep()
}


class BigStudent(): PlayGame , Sleep {
    override val playTime: Int = 0


    override fun playGame() {
        TODO("Not yet implemented")
    }

    override val sleepTime: Int = 0


    override fun sleep() {
        TODO("Not yet implemented")
    }

}





abstract class People(){
    abstract var name: String
    abstract fun say()
    fun study() {

    }
    var type = "people"

}
class BigPeople() : People() {
    override var name: String = ""
    override fun say() {
        println("i am big people")
    }
}


fun People.test() {
    println("People 的扩展函数")
}

// String的扩展函数
fun String.uppercase(): String {
    var ret = ""
    this.forEachIndexed { index, ch ->
        if ((index + 1) % 2 == 1) {
            ret = ret + ch.uppercaseChar()
        } else {
            ret = ret + ch.lowercaseChar()
        }
    }
    return ret
}

val Int.dp
    get(): Double {
        return 2.0 * this - 1.1
    }
