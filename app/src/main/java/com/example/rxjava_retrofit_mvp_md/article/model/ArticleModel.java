package com.example.rxjava_retrofit_mvp_md.article.model;

import android.content.Context;


public interface ArticleModel {
    void lodeArticles(Context context,String url, String page, String size, ArticleModelImpl.
            OnLoadArticleListListener onLoadArticleListListener);
}
