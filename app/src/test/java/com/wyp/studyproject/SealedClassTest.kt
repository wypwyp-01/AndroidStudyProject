package com.wyp.studyproject

import android.R
import com.wyp.studyproject.login.Slides
import org.junit.Test

class SealedClassTest {
    @Test
    fun main() {
//        val a = Video("/User/wyp/studyProject")
//        val b = Image(10)
//        val c = Slides()
//        process(a)
//
//
//        process(b)
//
//        process(c)
        val name: String by lazy {
            "aaa"
        }

    }
}


fun process(result: DownloadResult): Int {
    return when (result) {
        is Video-> {
            println(result.path.length)
            result.path.length
        }
        is Image-> {
            println(result.num)
            result.num
        }

    }

}


sealed class DownloadResult()

class Video(val path: String): DownloadResult()

open class Image(val num: Int): DownloadResult()



