package com.wyp.studyproject

import org.junit.Test

class MapTest {
    @Test
    fun main() {
        testMap()
    }
}

fun testMap() {
    // 定义map
    val s = mutableMapOf<Int,String>(
        10001 to "xiaoming",
        10002 to "xiaohong",
        10003 to "xiaogang"
    )
    val name1 = s[10001]
    val name = s.getOrDefault(10008,"default")
    println(name)
    println(name1)
    s[10001] = "hahaha"
    s[10004] = "lalala"
    println(s)

    val a = s.put(10001,"dududu")
    val b = s.put(10007,"lululu")
    println(a)
    println(b)
    println(s)

    if (s.contains(10001)) {
        println("包括10001")
    }
    if (s.containsKey(10001)) {
        println("包括10001")
    }
    if (10001 in s) {
        println("包括10001")
    }

    println(s.keys)
    println(s.values)

    for ((k,v) in s) {
        println(k)
        println(v)
    }

    s.forEach { i, string ->
        println(i)
        println(string)
    }


    val isRemove = s.remove(100333)
    println(isRemove)

    println(s)
    s.values.remove("xiaogang")   //直接从values中移除，会使得整个键值对被移除
    println(s)

    val map: MutableMap<Int, String> = mutableMapOf(
        10001 to "小明",
        10002 to "小红",
        10003 to "小明"   //这里存在两个一样的元素
    )
    map.values.remove("小明")   //通过这种方式移除也只会移除按顺序下来的第一个
    println(map)  // {10002=小红 (17), 10003=小明 (18)}

    val n = map.getOrElse(10005) {
        "xiaogang"
    }
    println(n)
    println(map)

    val n1 = map.getOrPut(10005) {
        "xiaogang"
    }
    println(n1)
    println(map)


}










