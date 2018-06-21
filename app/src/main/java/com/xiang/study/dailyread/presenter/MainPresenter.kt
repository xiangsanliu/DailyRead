package com.xiang.study.dailyread.presenter

import android.content.Context
import com.alibaba.fastjson.JSON
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.xiang.study.dailyread.model.adapter.StoryAdapter
import com.xiang.study.dailyread.model.bean.Date
import com.xiang.study.dailyread.view.MainView
import cz.msebera.android.httpclient.Header

/**
 * Created by xiang on 2017/8/8.
 */

class MainPresenter(private val context: Context) : BasePresenter<MainView>() {

    fun loadStoryList() {
        val client = AsyncHttpClient()
        client.get("https://news-at.zhihu.com/api/4/news/latest", object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val response = String(responseBody!!)
                val date = JSON.parseObject(response, Date::class.java)
                view.loadStoryList(StoryAdapter(date.stories!!))
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                showToast(context, error?.message!!);
            }
        })

    }


}
