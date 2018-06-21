package com.xiang.study.dailyread.model.bean

import com.alibaba.fastjson.annotation.JSONField

/**
 * Created by xiang on 2017/8/8.
 */

class TopStory {

    var images: Array<String>? = null

    var type: Int = 0

    var id: String? = null

    @JSONField(name = "ga_prefix")
    var gaPrefix: String? = null

    var title: String? = null

}
