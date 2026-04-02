package com.wyp.studyproject.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.Response
import java.io.File
import java.io.IOException

object MyOkHttpClient {


    fun testFileUpload(file: File,callback: (String?)-> Unit) {

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                file.name,
                file.asRequestBody("text/plain".toMediaType())
            )
            .addFormDataPart("name", "test")
            .build()


        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://httpbin.org/post")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                callback(body)
            }

        }




        )
    }






}



object Util {

    fun checkPermission(context: Context): Boolean {
        val permission = Manifest.permission.READ_MEDIA_AUDIO
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }



}