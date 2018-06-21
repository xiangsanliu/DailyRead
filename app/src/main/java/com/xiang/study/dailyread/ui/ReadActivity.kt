package com.xiang.study.dailyread.ui

import android.content.Intent
import android.graphics.Color
import android.support.design.widget.AppBarLayout
import android.view.Menu
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.xiang.study.dailyread.R
import com.xiang.study.dailyread.databinding.ActivityReadBinding
import com.xiang.study.dailyread.model.bean.Article
import com.xiang.study.dailyread.presenter.ReadPresenter
import com.xiang.study.dailyread.share.CollapsingToolbarLayoutState
import com.xiang.study.dailyread.view.ReadView

class ReadActivity : BaseActivity<ActivityReadBinding>(), ReadView {

    private val cssUrl = "file:///android_asset/style.css"

    private lateinit var presenter: ReadPresenter

    // 可折叠状态栏的状态
    private var state: CollapsingToolbarLayoutState = CollapsingToolbarLayoutState.EXPANDED

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_read, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun loadArticle(article: Article) {
        val collapsingToolbar = binding.toolbarLayout
        val articleTitle = binding.articleTitle
        binding.markdownView.loadMarkdown(article.body!!, cssUrl)
        Glide.with(this).load(article.image).into(binding.background)
        articleTitle.text = article.title
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == 0) {
                if (state != CollapsingToolbarLayoutState.EXPANDED) {
                    //修改状态标记为展开
                    state = CollapsingToolbarLayoutState.EXPANDED
                    //设置title为EXPANDED
                    collapsingToolbar.title = " "
                    articleTitle.text = article.title
                    invalidateOptionsMenu()
                }
            } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                    //设置title不显示
                    collapsingToolbar.title = article.title
                    //修改状态标记为折叠
                    state = CollapsingToolbarLayoutState.COLLAPSED
                    invalidateOptionsMenu()
                }
            } else {
                if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                    //设置title为INTERNEDIATE
                    collapsingToolbar.title = article.title
                    articleTitle.text = " "
                    //修改状态标记为中间
                    state = CollapsingToolbarLayoutState.INTERNEDIATE
                    invalidateOptionsMenu()
                }
            }
        })
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_share -> share(article)
            }
            return@setOnMenuItemClickListener true
        }
    }

    override fun initPresenter() {
        presenter = ReadPresenter(this)
        presenter.attachView(this)
    }

    override fun initData() {
        presenter.loadArticle(intent.getStringExtra("article_id"))
    }

    override fun initViews() {
        initStatusBar()
        initToolbar()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_read
    }

    /**
     * 设置状态栏透明
     */
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
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbarLayout.title = " "
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.setOnClickListener {
            binding.scrollView.smoothScrollTo(0, 0)
        }
    }

    private fun share(article: Article) {
        val shareIntent = Intent().setAction(Intent.ACTION_SEND).setType("text/plain")
        val shareText = article.title + '\n' + article.share_url;
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
        startActivity(Intent.createChooser(shareIntent, article.title))
    }

}
