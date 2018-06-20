package com.xiang.study.dailyread.view

import com.xiang.study.dailyread.model.adapter.StoryAdapter

/**
 * Created by xiang on 2017/8/8.
 */

interface MainView : BaseView {
    fun loadStoryList(adapter: StoryAdapter)
}
