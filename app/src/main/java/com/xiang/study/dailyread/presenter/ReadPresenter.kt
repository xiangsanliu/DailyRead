package com.xiang.study.dailyread.presenter

import android.content.Context
import android.util.Log
import com.alibaba.fastjson.JSON
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.xiang.study.dailyread.model.bean.Article
import com.xiang.study.dailyread.model.bean.Date
import com.xiang.study.dailyread.model.network.HttpGetter
import com.xiang.study.dailyread.view.ReadView
import cz.msebera.android.httpclient.Header
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by xiang on 2017/8/8.

 */

class ReadPresenter(private val context: Context, private val view: ReadView): BasePresenter() {

    override fun onCreate() {

    }

    fun loadAriticle(id: String) {
        val client: AsyncHttpClient = AsyncHttpClient()
        client.get("https://news-at.zhihu.com/api/4/news/"+id, object : AsyncHttpResponseHandler(){
            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
            }
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val article: Article = JSON.parseObject(String(responseBody!!), Article::class.java)
                view.loadArtile(article)
            }

        })
    }
}
