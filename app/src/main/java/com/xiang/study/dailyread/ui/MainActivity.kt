package com.xiang.study.dailyread.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.xiang.study.dailyread.R
import com.xiang.study.dailyread.databinding.ActivityMainBinding
import com.xiang.study.dailyread.presenter.MainPresenter
import com.xiang.study.dailyread.model.adapter.StoryAdapter
import com.xiang.study.dailyread.view.MainView


class MainActivity : BaseActivity<ActivityMainBinding>(), MainView {

    private lateinit var presenter: MainPresenter

    override fun loadStoryList(adapter: StoryAdapter) {
        binding.storyList.adapter = adapter
    }

    override fun initViews() {
        binding.storyList.layoutManager = LinearLayoutManager(this)
        setSupportActionBar(binding.toolbar)
    }

    override fun initPresenter() {
        presenter = MainPresenter(this)
        presenter.attachView(this)
    }

    override fun initData() {
        presenter.loadStoryList();
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main;
    }

}

