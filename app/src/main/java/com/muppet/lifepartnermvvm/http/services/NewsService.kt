package com.muppet.lifepartnermvvm.http.services

import com.muppet.lifepartnermvvm.Constants
import com.muppet.lifepartnermvvm.bean.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * des：新闻相关接口
 *
 * @author: Muppet
 * @date:   2021/2/19
 */
interface NewsService {

    /**
     * 根据 NewsType 获取新闻 (类型,shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚))
     */
    @GET("/toutiao/index?key=${Constants.API_NEWS_KEY}")
    fun getNews(@Query("type") NewsType: String) : retrofit2.Call<NewsResponse>

}