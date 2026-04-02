package com.wyp.studyproject


import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.junit.Test


class GsonTest {
    @Test
    fun main() {
        val map : Map<String,String> = mapOf(
            "a" to "1",
            "bb" to "2",
            "ccc" to "1"
        )


        val u1 = User().apply {
            testNullList = mutableListOf("111","222",null,"null","444")
            test = "121"
            username = "www"
            friend = mutableListOf("a","a","a","bb","ccc","dddd")
            testMap = map
        }
        val g = Gson()
        val json1 = g.toJson(u1)
        println(json1)
        val u2 = g.fromJson(json1,User::class.java)
        println("${u2.score},${u2.username}")

        val u3 = g.fromJson(json1,UserData::class.java)
        println(u3)






    }
}

















class User {
    @Expose(serialize = false)
    lateinit var testNullList: List<String?>
    lateinit var testMap: Map<String,String>
    lateinit var test: String
    // 四个属性
    var id: Long = 0
    @SerializedName("user_name")
    var username: String? = null
    var age: Int = 0
    var isVip: Boolean = false
    var score: Score = Score(2,6)
    lateinit var friend: MutableList<String>
}
data class UserData(
    var test: Int,
    val id: Long,
    @SerializedName("user_name")
    val username: String,
    val age: Int ,
    val isVip: Boolean,
    val friend: Set<String>?,
    val testMap: TestClass
)
data class Score(
     val math: Int,
    val english: Int,

)

data class TestClass(
    val a: Int,
    val bb: Int,
    val ccc: Int
)


