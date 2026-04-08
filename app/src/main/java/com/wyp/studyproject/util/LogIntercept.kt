package com.wyp.studyproject.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.http.promisesBody
import okio.Buffer
import java.nio.charset.Charset

class LogInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // 1. 获取当前请求
        val request: Request = chain.request()

        // ==================== 打印请求信息 ====================
        Log.d("OkHttp", "════════════════ 请求开始 ════════════════")
        Log.d("OkHttp", "请求方法: ${request.method}")
        Log.d("OkHttp", "请求URL: ${request.url}")

        // 打印请求头
        val headers = request.headers
        if (headers.size > 0) {
            Log.d("OkHttp", "请求头:")
            for (i in 0 until headers.size) {
                Log.d("OkHttp", "  ${headers.name(i)}: ${headers.value(i)}")
            }
        }

        // 打印请求体（POST参数）
        request.body?.let { body ->
            try {
                val buffer = Buffer()
                body.writeTo(buffer)
                val str = buffer.readString(Charset.forName("UTF-8"))
                if (str.isNotEmpty()) {
                    Log.d("OkHttp", "请求体：$str")
                }
            } catch (e: Exception) {
                Log.d("OkHttp", "请求体打印失败")
            }
        }

        // 2. 执行请求，拿到响应
        val response = chain.proceed(request)

        // ==================== 打印响应信息 ====================
        Log.d("OkHttp", "响应码: ${response.code}")
        Log.d("OkHttp", "是否成功: ${response.isSuccessful}")

        // 打印响应体
        response.body?.let { body ->
            val source = body.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer
            val charset = Charset.forName("UTF-8")
            Log.d("OkHttp", "响应体: ${buffer.clone().readString(charset)}")
        }
        Log.d("OkHttp", "════════════════ 请求结束 ════════════════\n")

        return response
    }
}