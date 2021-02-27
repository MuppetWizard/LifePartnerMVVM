package com.muppet.lifepartnermvvm

import androidx.lifecycle.liveData
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/**
 * des：app仓库类，用于向网络层或者本地数据库获取数据
 *
 * @author: Muppet
 * @date:   2021/2/21
 */
object Repository {



    /**
     * 对livedata的封装，统一处理try catch 将结果发射出去
     */
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }


}