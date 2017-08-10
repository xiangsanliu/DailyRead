package com.xiang.study.dailyread.model.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiang.study.dailyread.R;
import com.xiang.study.dailyread.model.bean.Story;
import com.xiang.study.dailyread.ui.ReadActivity;

import java.util.List;

/**
 * Created by xiang on 2017/8/9.
 *
 */

public class StoryAdapter extends BaseQuickAdapter<Story, BaseViewHolder> {

    public StoryAdapter(List<Story> data) {
        super(R.layout.item_story, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, final Story item) {
        Glide.with(mContext).load(item.getImages()[0]).into((ImageView) holder.getView(R.id.image));
        holder.setText(R.id.story_title, item.getTitle());
        CardView cardView = holder.getView(R.id.card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReadActivity.class);
                intent.putExtra("article_id", item.getId());
                mContext.startActivity(intent);
            }
        });
    }
}
