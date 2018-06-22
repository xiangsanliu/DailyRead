package com.xiang.study.dailyread.presenter

import android.content.Context
import com.alibaba.fastjson.JSON
import com.xiang.study.dailyread.model.HttpClient
import com.xiang.study.dailyread.model.bean.Article
import com.xiang.study.dailyread.view.ReadView

/**
 * Created by xiang on 2017/8/8.
 */

class ReadPresenter(private val context: Context) : BasePresenter<ReadView>() {

    fun loadArticle(id: String) {
        HttpClient.simpleGet("https://news-at.zhihu.com/api/4/news/$id", object : HttpClient.CallBack {
            override fun onSuccess(data: String) {
                val article: Article = JSON.parseObject(data, Article::class.java)
                view.loadArticle(article)
            }

            override fun onFailure(msg: String) {
                showToast(context, msg);
            }
        })
    }
}
