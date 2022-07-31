package com.example.myapplication.NewsModels;

import java.io.Serializable;
import java.util.List;

public class NewsAPIResponse {
    String status  = "";
    int totalResults;
    List<NewsArticle> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticle> newsArticles) {
        this.articles = newsArticles;
    }
}
