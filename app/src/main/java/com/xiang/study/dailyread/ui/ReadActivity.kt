package com.xiang.study.dailyread.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.xiang.study.dailyread.share.CollapsingToolbarLayoutState

import com.xiang.study.dailyread.R
import com.xiang.study.dailyread.databinding.ActivityReadBinding
import com.xiang.study.dailyread.model.bean.Article
import com.xiang.study.dailyread.presenter.ReadPresenter
import com.xiang.study.dailyread.view.ReadView
import us.feras.mdv.MarkdownView

class ReadActivity : AppCompatActivity(), ReadView {


    private lateinit var binding : ActivityReadBinding

    private lateinit var markdownView : MarkdownView
    private lateinit var toolbar : Toolbar
    private lateinit var fab : FloatingActionButton
    private lateinit var presenter : ReadPresenter
    private lateinit var imageView: ImageView
    private lateinit var collapsinToolbar: CollapsingToolbarLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var articleTitle: TextView
    private lateinit var scrollView : NestedScrollView
    private var state : CollapsingToolbarLayoutState = CollapsingToolbarLayoutState.EXPANDED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_read)
        initViews()
        presenter = ReadPresenter(this, this)
        presenter.onCreate()
        presenter.loadAriticle(intent.getStringExtra("article_id"))
    }

    override fun initViews() {
        initStatusBar()
        initToolbar()
        appBarLayout = binding.appBar
        scrollView = binding.scrollView
        markdownView = binding.articleContent
        imageView = binding.background
        fab = binding.fab

    }

    override fun loadArtile(article: Article) {
        loadContent(article.body!!)
        Glide.with(this).load(article.image).into(imageView)
        articleTitle.text = article.title
        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == 0) {
                if (state != CollapsingToolbarLayoutState.EXPANDED) {
                    //修改状态标记为展开
                    state = CollapsingToolbarLayoutState.EXPANDED
                    //设置title为EXPANDED
                    collapsinToolbar.title = " "
                    articleTitle.text = article.title
                    invalidateOptionsMenu()
                }
            } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                    //设置title不显示
                    collapsinToolbar.title = article.title
                    //修改状态标记为折叠
                    state = CollapsingToolbarLayoutState.COLLAPSED
                    invalidateOptionsMenu()
                }
            } else {
                if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                    //设置title为INTERNEDIATE
                    collapsinToolbar.title = article.title
                    articleTitle.text = " "
                    //修改状态标记为中间
                    state = CollapsingToolbarLayoutState.INTERNEDIATE
                    invalidateOptionsMenu()
                }
            }
        }
        val shareIntent  = Intent().setAction(Intent.ACTION_SEND).setType("text/plain")
        val shareText : String = article.title+ "\n" + article.share_url
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
        fab.setOnClickListener {
            startActivity(Intent.createChooser(shareIntent, article.title))
        }
        toolbar.setOnMenuItemClickListener { item ->
            val id = item.itemId
            when(id) {
                R.id.action_share -> startActivity(Intent.createChooser(shareIntent, article.title))
            }
            return@setOnMenuItemClickListener  true
        }

    }

    private fun loadContent(content: String) {
        markdownView.loadMarkdown(content, "file:///android_asset/style.css")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun initStatusBar() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        collapsinToolbar = findViewById(R.id.toolbar_layout)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        articleTitle = findViewById(R.id.article_title)
        collapsinToolbar.title = " "
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.setOnClickListener {
            scrollView.smoothScrollTo(0, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_read, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu!!.findItem(R.id.action_share).isVisible = state == CollapsingToolbarLayoutState.COLLAPSED
        return super.onPrepareOptionsMenu(menu)
    }

}
