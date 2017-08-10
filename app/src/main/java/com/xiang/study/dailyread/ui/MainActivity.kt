package com.xiang.study.dailyread.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.xiang.study.dailyread.R
import com.xiang.study.dailyread.presenter.MainPresenter
import com.xiang.study.dailyread.model.adapter.StoryAdapter
import com.xiang.study.dailyread.view.MainView
import us.feras.mdv.MarkdownView


class MainActivity : AppCompatActivity(), MainView {
    lateinit var presenter: MainPresenter
    lateinit var storyList : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        presenter = MainPresenter(this, this)
        presenter.onCreate()
    }

    override fun loadStoryList(adapter: StoryAdapter) {
        storyList.adapter = adapter
    }

    override fun initViews() {
        storyList = findViewById(R.id.story_list) as RecyclerView
        storyList.layoutManager = LinearLayoutManager(this)
    }

}

