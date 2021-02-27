package com.muppet.lifepartnermvvm.http

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.muppet.lifepartnermvvm.BuildConfig
import com.muppet.lifepartnermvvm.Constants
import com.muppet.lifepartnermvvm.http.services.WeatherService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * des：Retrofit构建类
 *
 * @author: Muppet
 * @date:   2021/2/19
 */
object ServicesCreator {

    private var isWeatherAPI = false

    /**
     * 构建Retrofit
     * 由于部分业务使用的url的不同，故做两种处理
     */
    private val retrofit: Retrofit
        get() {
            val retrofit = Retrofit.Builder()

            if (isWeatherAPI)
                retrofit.baseUrl(Constants.BASE_WEATHER_URL)
            else
                retrofit.baseUrl(Constants.BASE_URL)

            retrofit
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient().build())

            return retrofit.build()
        }

    /**
     * 构建本地的请求和响应数据的拦截打印
     */
    private fun getClient() : OkHttpClient.Builder{
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder
            .connectTimeout(
                Constants.DEFAULT_TIMEOUT.toLong(),
                TimeUnit.MILLISECONDS
            )
            .readTimeout(
                Constants.DEFAULT_TIMEOUT.toLong(),
                TimeUnit.MILLISECONDS
            )
            .writeTimeout(
                Constants.DEFAULT_TIMEOUT.toLong(),
                TimeUnit.MILLISECONDS
            )

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .request("请求")
                .response("响应")
                .build()
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return httpClientBuilder
    }


    fun <T> create(serviceClass: Class<T>): T {
        isWeatherAPI = serviceClass == WeatherService::class.java
        return retrofit.create(serviceClass)
    }

    /**
     * 通过泛型实化，实现类似：ServicesCreator.create<WeatherService>()
     * 的方式来构建请求接口
     */
    inline fun <reified T> create(): T = create(T::class.java)
}