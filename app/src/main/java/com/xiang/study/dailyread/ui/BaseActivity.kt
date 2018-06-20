package com.xiang.study.dailyread.ui

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        initViews()
        initPresenter()
        initData()
    }

    abstract fun initViews()

    abstract fun initPresenter()

    abstract fun initData()

    abstract fun getLayoutId(): Int;

}