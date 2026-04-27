package com.wyp.studyproject

import org.junit.Test

class ArrayTest {
    @Test
    fun createArray() {
        // 数组的创建
        val arr = arrayOf(1,2,3,6,7)
        arr.forEach {
            print("${it} ")
        }
        print("\n")
        // 查找
        println(arr[0])
        println(arr.get(4))

        // 修改
        arr[0] = 10
        arr.set(1,100)
        arr.forEach {
            print("${it} ")
        }
        print("\n")

        // 遍历
        for (element in arr.indices) {
            println(arr[element])
        }

        for ((index,ele) in arr.withIndex()) {
            println("arr[$index] = $ele")
        }

        println(arr.joinToString("--",">","<") {
            (it * it ).toString()
        })

        val arr1 = arrayOf(2,4)
        for (index in arr1.indices) {
            println(arr1[index])
        }


        val arr2 = Array(5) {
            it * it
        }
        for (index in arr2.indices) {
            println(arr2[index])
        }

        val l = List(4) {
            "我是第$it 个元素"
        }
        println(l)


        val arr3 = arrayOfNulls<Int>(7)
        val arr4 = emptyArray<Int>()
    }

    @Test
    fun operateArrsy() {
        val arr1 = arrayOf(1,2,3,6,7)
        val arr2 = arrayOf(1,2,3,6,7)
        // 比较内容是否相等
        println(arr1.contentEquals(arr2))
        // copy数组
        val arr3 = arr1.copyOf()
        val arr4 = arr1
        println(arr1 === arr3)
        println(arr1 === arr4)
        // copy数组  指定范围
        val arr5 = arr1.copyOfRange(2,4)
        println(arr5.joinToString())
        // 判断是否包含某个元素  最后会调用 == ，所以要重写Equals
        println(arr1.contains(1))
        // 寻找某个元素的位置  如果不存在  返回-1
        println(arr1.indexOf(1))
        println(arr1.first())
        println(arr1.last())
        // 反转数组
        println(arr1.reversed().joinToString())
        // 倒序排序
        arr1.sortDescending()
        println(arr1.joinToString())
        // 随机洗牌
        arr1.shuffle()
        println(arr1.joinToString())
    }


    @Test
    fun 可变长参数() {
        test("ste",1,2,3,4,6,5,7,9)
        val a = intArrayOf(1,2,3,4)
        test("wer",*a)

    }

    // 使用vararg声明可变长参数
    fun test(str: String, vararg nums: Int) {
        val intArray = nums
        println(intArray.joinToString())
        intArray[0] = 10
        println(intArray.joinToString())
    }

    @Test
    fun 多维数组() {
        val arr = arrayOf(intArrayOf(1,2,3,4,5),intArrayOf(6,7,8,9,0))
        // 多维数组遍历
        for (i in arr.indices) {
            for (j in arr[0].indices) {
                print("${arr[i][j]} ")
            }
            print("\n")
        }
        val arr1 = arrayOf(intArrayOf(1,2,3,4,5),intArrayOf(6,7,8,9,0))
        println(arr.contentEquals(arr1))
        // 多维数组比较要使用contentDeepEquals
        println(arr.contentDeepEquals(arr1))
    }






}