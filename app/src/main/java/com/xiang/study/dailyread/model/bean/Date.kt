package com.xiang.study.dailyread.model.bean

import com.alibaba.fastjson.annotation.JSONField

/**
 * Created by xiang on 2017/8/8.
 *
 */
class Date {

    var date: String? = null

    var stories: List<Story>? = null

    @JSONField(name = "top_stories")
    var topStories: List<TopStory>? = null

}
