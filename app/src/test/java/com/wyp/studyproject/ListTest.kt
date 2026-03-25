package com.wyp.studyproject

import org.junit.Test
import kotlin.math.*
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ListTest {
    @Test
    fun main() {
        // listText()
        // 实用函数
        // function()





    }

    fun function() {
//        val text = readln()
//        println(text)
        println(2.0.pow(4.0)) // 次方
        println(abs(-4)) // 绝对值
        println(max(-4,2)) // 绝对值
        println(E) // 绝对值
        println(PI) // 绝对值
        println(sin(PI / 2))// 三角函数
        println(ln(4.0) / ln(2.0)) // 对数
        println(ceil(2.3)) // 向上取整
        println(floor(-2.3)) // 向下取整
    }


    fun listText() {
        // 创建可变集合
        val l1 = mutableListOf(1,2,3,4,5)
        println(l1)
        // 增
        l1.add(6)
        l1.add(0,1)
        val toAdd = mutableListOf(7,8,9,10)
        l1.addAll( mutableListOf(7,8,9,10))
        println(l1)
        // 删
        // [1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        val isremove = l1.remove(13) // 返回的是有没有删除
        val remove = l1.removeAt(0) // 返回的是被删除的元素的值
        println(l1)
        println(remove)
        println(isremove)
        l1.removeAll { it > 5 } // 按照条件删除
        println(l1)
        // 改
        l1[0] = 2
        println(l1)
        // 查
        println(l1.get(1))
        // 其他操作
        val l2 = mutableListOf<Int>(5,4,3,2,1)
        val sublist = l2.subList(1, 3) // 截取  左闭右开
        println(sublist)
        l2.sort() // 排序 升序
        println(l2)
        val l3 = mutableListOf<Int>(4,3,6,5,4,9,7,6)
        l3.sortDescending()// 排序 降序
        println(l3)
        println(l3.any { it > 8 }) // 任意  只要有一个满足条件，就返回true
        println(l3.all { it > 8 })// 全部  只要有一个不满足条件，就返回false




        // list的遍历
        val list = mutableListOf("adf","dfb","cgh")

        for (char in list) {
            println(char)
        }

        list.forEach { println(it.uppercase()) }

        list.forEachIndexed { index, string ->
            println("list[$index] = $string")
        }
    }
}