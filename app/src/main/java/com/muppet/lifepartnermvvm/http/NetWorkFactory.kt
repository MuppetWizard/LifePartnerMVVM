package com.muppet.lifepartnermvvm.http

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * des：网络请求工厂类，用于向服务器发送请求，并将数据返回到仓库层
 *
 * @author: Muppet
 * @date:   2021/2/21
 */
object NetWorkFactory {


    /**
     * 扩展函数，通过协程将每次服务端返回的数据的回调进行封装
     */
    private suspend fun <T> Call<T>.await() : T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    body?.let { continuation.resume(body) }
                        ?: continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}