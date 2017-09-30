package com.cdlixin.coc.ui.news.view;

import com.cdlixin.coc.entity.NewsEntity;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/9/29.
 */

public interface NewsListView {
    void showNews(List<NewsEntity> newsEntities);
    void showToast(String string);
}
