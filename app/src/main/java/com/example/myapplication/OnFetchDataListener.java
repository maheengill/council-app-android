package com.example.myapplication;

import com.example.myapplication.NewsModels.NewsArticle;

import java.util.List;

public interface OnFetchDataListener<NewsAPIResponse> {

    void onFetchData(List<NewsArticle> articles, String msg);

    void onError(String message);
}
