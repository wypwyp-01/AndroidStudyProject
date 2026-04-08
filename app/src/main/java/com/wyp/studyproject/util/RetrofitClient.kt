package com.wyp.studyproject.util


import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface RetrofitApi{
    @GET("posts/1")
    suspend fun testApi(
//        @Query("test") test: Int, // 参数
    ): RetrofitResponse


    @POST("posts")
    suspend fun testPost(
        @Body body:RetrofitRequest
    ): RetrofitResponse
}
object RetrofitClient {
    val okHttpClient = OkHttpClient.Builder().addInterceptor(LogInterceptor()).build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(RetrofitApi::class.java)
}
data class RetrofitResponse(
    @SerializedName("userId")
    val userid: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null
)
data class RetrofitRequest(
    val userId: Int? = null,
    val title: String? = null
)