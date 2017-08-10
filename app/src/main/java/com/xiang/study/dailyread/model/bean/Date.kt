package com.xiang.study.dailyread.model.bean

import android.util.Log
import java.util.*

/**
 * Created by xiang on 2017/8/8.
 *
 */

class Date {
    var date: String? = null
    var stories: List<Story>? = null
    var top_stories: List<TopStory>? = null
    override fun toString(): String {
        for (story : Story in stories!!) {
            Log.d("title", story.title)
            Log.d("id", story.id)
            Log.d("type", story.type.toString())
        }

        for (top_story : TopStory in top_stories!!) {
            Log.d("title", top_story.title)
            Log.d("id", top_story.id)
            Log.d("type", top_story.type.toString())
        }

        return date!!
    }
}
