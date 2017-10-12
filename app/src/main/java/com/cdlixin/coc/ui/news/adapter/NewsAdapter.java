package com.cdlixin.coc.ui.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdlixin.coc.R;
import com.cdlixin.coc.entity.NewsEntity;
import com.cdlixin.coc.utils.ImageUtil;
import com.cdlixin.coc.utils.TimeUtil;

import java.util.List;

import butterknife.Bind;

/**
 * Created by 蒲弘宇 on 2017/9/30.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHoudler> {

    private List<NewsEntity> newsList;
    private Context context;

    public NewsAdapter(List<NewsEntity> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public ViewHoudler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHoudler(view);
    }

    @Override
    public void onBindViewHolder(ViewHoudler holder, int position) {
        NewsEntity news = newsList.get(position);
        String picUrl = news.getInfo_pic_url();
        if(picUrl != null){
            ImageUtil.loadImg(context,picUrl,holder.imgNews);
        }
        holder.tvTitle.setText(news.getInfo_title());
        String source = news.getInfo_source();
        if(TextUtils.isEmpty(source)){
            holder.tvSource.setText("来源:未知");
        }else {
            if(source.length()>6){
                source = source.substring(0,6)+"...";
            }
            holder.tvSource.setText("来源:"+ source);
        }
        String timeToShow = TimeUtil.getShowTime(news.getInfo_date());
        holder.tvPublishTime.setText(timeToShow);

        //筛选下期实现
        holder.imgFilter.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    class ViewHoudler extends RecyclerView.ViewHolder {
        private ImageView imgNews;
        private TextView tvTitle;
        private TextView tvSource;
        private ImageView imgFilter;
        private TextView tvPublishTime;
        public ViewHoudler(View itemView) {
            super(itemView);
            imgNews = (ImageView) itemView.findViewById(R.id.img_news);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            imgNews = (ImageView) itemView.findViewById(R.id.img_news);
            tvSource = (TextView) itemView.findViewById(R.id.tv_source);
            imgFilter = (ImageView) itemView.findViewById(R.id.img_filter);
            tvPublishTime = (TextView) itemView.findViewById(R.id.tv_publishTime);
        }
    }
}
