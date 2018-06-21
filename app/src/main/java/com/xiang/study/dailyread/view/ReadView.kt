package com.xiang.study.dailyread.view

import com.xiang.study.dailyread.model.bean.Article


/**
 * Created by xiang on 2017/8/8.
 *
 */

interface ReadView : BaseView {
    fun loadArticle(article: Article)
}
