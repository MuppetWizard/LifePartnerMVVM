package com.muppet.lifepartnermvvm.bean

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    val reason: String, // 成功的返回
    val result: Result,
    @SerializedName("error_code")
    val errorCode: Int // 0
) {
    data class Result(
        val stat: String, // 1
        val data: List<Data>
    )
    data class Data(
        val uniquekey: String, // 6c4caa0c3ba6e05e2a272892af43c00e
        val title: String, // 杨幂的发际线再也回不去了么？网友吐槽像半秃
        val date: String, // 2017-01-05 11:03
        val category: String, // yule
        val author_name: String, // 腾讯娱乐
        val url: String, // http://mini.eastday.com/mobile/170105110355287.html?qid=juheshuju
        val thumbnail_pic_s: String, // http://03.imgmini.eastday.com/mobile/20170105/20170105110355_806f4ed3fe71d04fa452783d6736a02b_1_mwpm_03200403.jpeg
        val thumbnail_pic_s02: String, // http://03.imgmini.eastday.com/mobile/20170105/20170105110355_806f4ed3fe71d04fa452783d6736a02b_2_mwpm_03200403.jpeg
        val thumbnail_pic_s03: String // http://03.imgmini.eastday.com/mobile/20170105/20170105110355_806f4ed3fe71d04fa452783d6736a02b_3_mwpm_03200403.jpeg
    )
}