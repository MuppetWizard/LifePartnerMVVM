package com.muppet.lifepartnermvvm

import android.app.Application
import android.content.Context

/**
 * des：
 *
 * @author: Muppet
 * @date:   2021/3/1
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        baseApplication = applicationContext
    }

    companion object{
        private lateinit var baseApplication: Context

        fun getAppContext(): Context{
            return baseApplication
        }
    }
}