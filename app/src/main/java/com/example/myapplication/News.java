package com.example.myapplication;


import com.google.gson.annotations.SerializedName;

public class News {
    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private long totalResults;

    @SerializedName("articles")
    private Article[] articles;

    public String getStatus() {
        return status;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public Article[] getArticles() {
        return articles;
    }
}
