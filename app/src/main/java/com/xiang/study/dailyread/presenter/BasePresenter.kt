package com.xiang.study.dailyread.presenter

import android.content.Context
import android.widget.Toast
import com.xiang.study.dailyread.view.BaseView

/**
 * Created by xiang on 2017/8/9.
 *
 */

abstract class BasePresenter<T : BaseView> {

    protected lateinit var view: T

    fun attachView(view: T) {
        this.view = view;
    }

    protected fun showToast(context: Context, toast: String) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
    }
}
