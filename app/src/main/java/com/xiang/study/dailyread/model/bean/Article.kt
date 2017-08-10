package com.xiang.study.dailyread.model.bean

import java.io.Serializable

/**
 * Created by xiang on 2017/8/8.

 */

class Article : Serializable {
    var body: String? = null
    var image_source: String? = null
    var title: String? = null
    var image: String? = null
    var share_url: String? = null
    var js: List<String>? = null
    var ga_prefix: String? = null
    var images: List<String>? = null
    var type: Int = 0
    var id: String? = null
    var css: List<String>? = null
}
