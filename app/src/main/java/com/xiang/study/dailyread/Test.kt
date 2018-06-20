package com.xiang.study.dailyread

import android.support.design.widget.AppBarLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View

import com.alibaba.fastjson.JSON
import com.chad.library.adapter.base.BaseQuickAdapter
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.xiang.study.dailyread.model.adapter.StoryAdapter
import com.xiang.study.dailyread.model.bean.Article
import com.xiang.study.dailyread.model.bean.Date
import com.xiang.study.dailyread.model.network.HttpGetter
import com.xiang.study.dailyread.presenter.MainPresenter

import cz.msebera.android.httpclient.Header
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by xiang on 2017/8/8.
 */

class Test {
    private fun test() {}

    private fun test1(): Int {
        return 1
    }
}
