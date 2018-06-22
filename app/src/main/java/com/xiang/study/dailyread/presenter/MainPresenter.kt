package com.xiang.study.dailyread.presenter

import android.content.Context
import com.alibaba.fastjson.JSON
import com.xiang.study.dailyread.model.HttpClient
import com.xiang.study.dailyread.model.adapter.StoryAdapter
import com.xiang.study.dailyread.model.bean.Date
import com.xiang.study.dailyread.view.MainView

/**
 * Created by xiang on 2017/8/8.
 */

class MainPresenter(private val context: Context) : BasePresenter<MainView>() {

    fun loadStoryList() {
        HttpClient.simpleGet("https://news-at.zhihu.com/api/4/news/latest", object : HttpClient.CallBack {
            override fun onSuccess(data: String) {
                val date = JSON.parseObject(data, Date::class.java)
                view.loadStoryList(StoryAdapter(date.stories!!))
            }

            override fun onFailure(msg: String) {
                showToast(context, msg);
            }
        })
    }
}
