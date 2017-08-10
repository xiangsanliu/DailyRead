package com.xiang.study.dailyread.ui

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.xiang.study.dailyread.CollapsingToolbarLayoutState

import com.xiang.study.dailyread.R
import com.xiang.study.dailyread.model.bean.Article
import com.xiang.study.dailyread.presenter.ReadPresenter
import com.xiang.study.dailyread.view.ReadView
import us.feras.mdv.MarkdownView

class ReadActivity : AppCompatActivity(), ReadView {


    lateinit var markdownView : MarkdownView
    lateinit var toolbar : Toolbar
    lateinit var fab : FloatingActionButton
    lateinit var presenter : ReadPresenter
    lateinit var imageView: ImageView
    lateinit var collapsinToolbar: CollapsingToolbarLayout
    lateinit var appBarLayout: AppBarLayout
    lateinit var articleTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        initViews()
        presenter = ReadPresenter(this, this)
        presenter.onCreate()
        presenter.loadAriticle(intent.getStringExtra("article_id"))
    }

    override fun initViews() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(Color.TRANSPARENT)
        window.setNavigationBarColor(Color.TRANSPARENT)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        appBarLayout = findViewById(R.id.app_bar) as AppBarLayout
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        markdownView = findViewById(R.id.article_content) as MarkdownView
        imageView = findViewById(R.id.background) as ImageView
//        fab = findViewById(R.id.fab) as FloatingActionButton
        collapsinToolbar = findViewById(R.id.toolbar_layout) as CollapsingToolbarLayout
        articleTitle = findViewById(R.id.article_title) as TextView
        collapsinToolbar.title = " "
        toolbar.setNavigationOnClickListener { e -> finish() }
//        fab.setOnClickListener { e ->
//
//        }
    }

    override fun loadArtile(article: Article) {
        loadContent(article.body!!)
        Glide.with(this).load(article.image).into(imageView)
        var state : CollapsingToolbarLayoutState = CollapsingToolbarLayoutState.EXPANDED
        articleTitle.setText(article.title)
        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == 0) {
                if (state != CollapsingToolbarLayoutState.EXPANDED) {
                    state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    collapsinToolbar.setTitle(" ");//设置title为EXPANDED
                    articleTitle.setText(article.title)
                }
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                    collapsinToolbar.setTitle(article.title);//设置title不显示
                    state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                }
            } else {
                if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                    collapsinToolbar.setTitle(article.title);//设置title为INTERNEDIATE
                    articleTitle.setText(" ")
                    state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                }
            }
        }
    }

    fun loadContent(content: String) {
        markdownView.loadMarkdown(content, "file:///android_asset/style.css")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
