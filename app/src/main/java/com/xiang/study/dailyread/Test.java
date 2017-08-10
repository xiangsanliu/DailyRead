package com.xiang.study.dailyread;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiang.study.dailyread.model.adapter.StoryAdapter;
import com.xiang.study.dailyread.model.bean.Article;
import com.xiang.study.dailyread.model.bean.Date;
import com.xiang.study.dailyread.model.network.HttpGetter;
import com.xiang.study.dailyread.presenter.MainPresenter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiang on 2017/8/8.
 *
 */

public class Test {
    private void test() {
    }
}
