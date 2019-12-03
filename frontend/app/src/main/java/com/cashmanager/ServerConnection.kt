package com.cashmanager

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AsynchronousGet {
    private val client = OkHttpClient()

    fun run(id: String?) {
        val request = Request.Builder()
            .url("http://18.191.247.228:8080/clients/"+id)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }
                    if (response.code != 200){
                        println(response.body!!.string() + "id : " + id)
                } else {
                        println("echec")
                    }
                }
            }
        })
    }
}
