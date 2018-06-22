package com.xiang.study.dailyread.model

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header

object HttpClient {

    fun simpleGet(url: String, callBack: CallBack) {
        AsyncHttpClient().get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                callBack.onSuccess(String(responseBody!!))
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                callBack.onFailure(error?.message!!);
            }

        })
    }

    interface CallBack {
        fun onSuccess(data: String)
        fun onFailure(msg: String)
    }
}