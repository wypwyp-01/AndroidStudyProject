package com.wyp.studyproject

import android.os.SystemClock.sleep
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.junit.Test
import kotlin.system.measureTimeMillis

class CoroutineStartTest {
    @Test
    // 需求：j1执行完毕之后再启动j2 j3
    fun testCoroutineJoin() = runBlocking{
        val j1 = launch {
            delay(2000)
            println("ONE")
        }
        j1.join()
        val j2 = launch {
            delay(2000)
            println("TWO")
        }
        val j3 = launch {
            delay(2000)
            println("THREE")
        }
    }

    @Test
    fun testCoroutineAwait() = runBlocking{
        val j1 = async {
            delay(2000)
            println("ONE")
        }
        j1.await()
        val j2 = async {
            delay(2000)
            println("TWO")
        }
        val j3 = async {
            delay(2000)
            println("THREE")
        }
    }
    // 结构化并发
    @Test
    fun testSync() = runBlocking {
        // 需求
        val time = measureTimeMillis {
            val one = async {
                println("one")
                doOne()
            }
            val two = async {
                println("two")
                doTwo()
            }
            println("result = ${one.await() + two.await()}")
        }
        println("time = $time")
    }
    suspend fun doOne(): Int {
        println("doOne")
        delay(1000)
        return 8
    }
    suspend private fun doTwo(): Int {
        println("doTwo")
        delay(5000)
        return 5
    }


    @Test
    fun startModeDefault() = runBlocking {
        // 启动协程  挂起
        val j1 = launch(start = CoroutineStart.DEFAULT) {
            delay(10000)
            println("job finished")
        }
        // 继续执行下面的任务，并不是并发执行的
        delay(1000)
        j1.cancel()
    }

    @Test
    fun startModeAtomic() = runBlocking {
        // 启动协程  挂起
        val j1 = launch(start = CoroutineStart.UNDISPATCHED) {
            // 前面 耗时任务
            println("j1")
            val time = System.currentTimeMillis()
            var sum = 0
            for (i in (1..20)) {
                for (j in (1..i)) {
                    sum += i * j
                }
            }
            println("2 ${Thread.currentThread()}")
            println("1 ${System.currentTimeMillis() - time}")
            // 这里是第一个挂起点
            delay(10000)
            println("job finished")
        }
        println("继续执行")
        println("3 ${Thread.currentThread()}")
        j1.cancel()
    }

    @Test
    fun testLazy(): Unit = runBlocking {
        val job = async(start = CoroutineStart.LAZY) {
            println("执行async")
            20
        }
        val time = System.currentTimeMillis()
        var sum = 0
        for (i in (1..150000)) {
            for (j in (1..i)) {
                sum += i * j
            }
        }
        println("${System.currentTimeMillis() - time}")

        job.await()
        job.cancel()
    }

    @Test
    fun testUndispatched(): Unit = runBlocking {
        launch(Dispatchers.IO,start = CoroutineStart.DEFAULT) {
            println("thread = ${Thread.currentThread()}")
        }
        // thread = Thread[#46,DefaultDispatcher-worker-1 @coroutine#2,5,main]
        // thread = Thread[#1,Test worker @coroutine#2,5,main]
    }

    @Test
    fun testScopeBuilder() = runBlocking {

            val j1 = launch {
                delay(4000)
                println("j1")
            }

            val j2 = launch {
                delay(2000)
                println("j2")
                throw Exception()
            }
            println("main")

        println("testScopeBuilder")
    }









}



