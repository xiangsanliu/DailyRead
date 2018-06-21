package com.xiang.study.dailyread.model.bean

import com.alibaba.fastjson.annotation.JSONField
import java.io.Serializable

/**
 * Created by xiang on 2017/8/8.

 */

class Article : Serializable {

    var body: String? = null

    @JSONField(name = "image_source")
    var imageSource: String? = null

    var title: String? = null

    var image: String? = null

    var share_url: String? = null

    var js: List<String>? = null

    @JSONField(name = "ga_prefix")
    var gaPrefix: String? = null

    var images: List<String>? = null

    var type: Int = 0

    var id: String? = null

    var css: List<String>? = null

}
