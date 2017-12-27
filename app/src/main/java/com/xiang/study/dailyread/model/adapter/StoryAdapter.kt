package com.xiang.study.dailyread.model.adapter

import android.content.Intent
import android.support.v7.widget.CardView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xiang.study.dailyread.R
import com.xiang.study.dailyread.model.bean.Story
import com.xiang.study.dailyread.ui.ReadActivity

/**
 * Created by xiang on 2017/8/9.
 *
 */

class StoryAdapter(data: List<Story>) : BaseQuickAdapter<Story, BaseViewHolder>(R.layout.item_story, data) {

    override fun convert(holder: BaseViewHolder, item: Story) {
        Glide.with(mContext).load(item.images!![0]).into(holder.getView<View>(R.id.image) as ImageView)
        holder.setText(R.id.story_title, item.title)
        val cardView = holder.getView<CardView>(R.id.card_view)
        cardView.setOnClickListener {
            val intent = Intent(mContext, ReadActivity::class.java)
            intent.putExtra("article_id", item.id)
            mContext.startActivity(intent)
        }
    }
}
