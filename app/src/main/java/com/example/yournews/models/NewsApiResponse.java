package com.example.yournews.models;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {
    String status="";
    int totalResults;
    List<API_data> articles;

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

    public List<API_data> getArticles() {
        return articles;
    }

    public void setArticles(List<API_data> articles) {
        this.articles = articles;
    }


}
