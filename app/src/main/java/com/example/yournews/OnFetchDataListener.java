package com.example.yournews;

import com.example.yournews.models.API_data;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    public void onFetchData(List<API_data> list, String message);
    public void onError(String message);


}
