package com.wyp.studyproject

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.wyp.studyproject.databinding.ActivityMainBinding
import com.wyp.studyproject.databinding.InternetTestBinding
import com.wyp.studyproject.util.LogInterceptor
import com.wyp.studyproject.util.MyOkHttpClient
import com.wyp.studyproject.util.RetrofitClient
import com.wyp.studyproject.util.RetrofitRequest
import com.wyp.studyproject.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.File
import java.io.IOException

class InternetActivity: AppCompatActivity() {
    private lateinit var binding: InternetTestBinding

    lateinit var okHttpClient: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InternetTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        okHttpClient = OkHttpClient.Builder().addInterceptor(LogInterceptor()).build()
        if (!Util.checkPermission(this)) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_MEDIA_AUDIO),
                100
            )
        } else {

        }

        binding.buttonOkhttpGet.setOnClickListener {
            // testGetTongbu()
            testPostYibu()
        }

        binding.buttonClear.setOnClickListener {
            binding.textReceiveOkhttp.text = ""
        }

        binding.buttonFileUpload.setOnClickListener {
            fileUpload()
        }

        binding.buttonRetrofitGet.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val response = RetrofitClient.retrofit.testApi()
                    binding.textReceiveOkhttp.text = response.toString()
                } catch (e : Exception) {
                    binding.textReceiveOkhttp.text = e.toString()
                }

            }

        }

        binding.buttonRetrofitPost.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val response = RetrofitClient.retrofit.testPost(RetrofitRequest(2,"wyp"))
                    binding.textReceiveOkhttp.text = response.toString()
                } catch (e : Exception) {
                    binding.textReceiveOkhttp.text = e.toString()
                }
            }
        }

    }



    @RequiresApi(Build.VERSION_CODES.O)
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            // 上传文件
            // fileUpload()
        } else {
            Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show()
        }
    }




private fun fileUpload() {
    val file = File(filesDir, "test.txt")
    if (!file.exists()) {
        file.createNewFile()
    }
    file.writeText("hello")
    MyOkHttpClient.testFileUpload(file) {str ->
        runOnUiThread {
            binding.textReceiveOkhttp.text = str
        }
    }
}

    private fun testPostYibu() {
        val requestBody = UseridBody(1)
        val gson = Gson()
        val json = gson.toJson(requestBody)
        val postBody: RequestBody = json.toRequestBody("application/json".toMediaType())

//        val formBody = FormBody.Builder()
//            .add("title", "foo")
//            .add("body", "bar")
//            .add("userId", "1")
//            .build()
        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/posts")
            .post(postBody)
            .build()

        okHttpClient.newCall(request).enqueue( object: Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
               //   val response = okHttpClient.newCall(request).execute()
                val body = response.body?.string()
                runOnUiThread {
                    binding.textReceiveOkhttp.text = body
                }
            }
        })
    }

    private fun testGetTongbu() {
        val start = System.currentTimeMillis()
        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/posts/1")
            .build() // 默认是get方法
        Thread {
            run {
                val body = try {
                    val response = okHttpClient.newCall(request).execute()
                    val body = response.body?.string()
                    runOnUiThread {
                        binding.textReceiveOkhttp.text = body
                        val time = System.currentTimeMillis() - start
                        binding.textTime.text = "耗时：" + time.toString() + "ms"
                    }
                } catch (e: Exception) {
                    binding.textReceiveOkhttp.text = "发生错误，$e"
                }

            }
        }.start()
    }
}
data class Response(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val completed: Boolean? = null
)

data class UseridBody(
    val userId: Int
)
