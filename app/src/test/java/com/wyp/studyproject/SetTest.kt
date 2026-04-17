package com.wyp.studyproject

import org.junit.Test

class SetTest {
    @Test
    fun testSet() {
        val s = mutableSetOf<Int>(1,3,5,5,7)
        println(s) // [1, 3, 5, 7]
        s.add(2)
        println(s) // [1, 3, 5, 7, 2] 默认在尾部

        val stu = mutableSetOf<Student>(Student("w",1),Student("w",1))
        println(stu)
    }



    @Test
    fun operateSet() {
        val s1 = mutableSetOf<Int>(1,3,5,7)
        val s2 = mutableSetOf<Int>(1,3,9,11)
        println(s1 union s2) // [1, 3, 5, 7, 9, 11] 并集
        println(s1 subtract s2) // [5, 7] 差集  在s1不在s2
        println(s1 intersect s2) // [1, 3] 交集
        println(s1 - s2) // 同subtract
    }

    @Test
    fun testIterator() {
        val l = mutableListOf(1,2,3,4,5,6,6,6,7)
        val it = l.listIterator()
        while (it.hasNext()) {
            val num = it.next()
            val index = it.nextIndex()
            println("index = $index,num = $num")
        }
        while (it.hasPrevious()) {
            val num = it.previous()
            val pr = it.previousIndex()
            println("pr = $pr,num = $num")
        }

    }



    class Test1 : Iterable<String> {    //这个接口实不实现其实都无所谓
        //实现这个运算符重载函数iterator是必须要的，否则不支持
        override operator fun iterator(): Iterator<String> = TestIterator()

        class TestIterator: Iterator<String> {  //自己随便写一个迭代器实现
            override fun hasNext(): Boolean = true
            override fun next(): String = "666"
        }
    }

    @Test
    fun main() {
        val test = Test1()
        for (s in test) {
            println(s)
        }
    }


    @Test
    fun testRange() {
        val l1 = mutableListOf<Int>(1,2,3,4,5)
        val it: MutableIterator<Int> = l1.iterator()
        while (it.hasNext()) {
            val num = it.next()
            it.remove()
        }
        println("size = ${l1.size}")
    }












}