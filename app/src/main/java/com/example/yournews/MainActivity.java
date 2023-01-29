package com.example.yournews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.yournews.adapter.CustomAdapter;
import com.example.yournews.models.API_data;
import com.example.yournews.models.NewsApiResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_list;
    CustomAdapter adapter;
    ProgressDialog dialog;
    SearchView search_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog=new ProgressDialog(this);
        dialog.setTitle("Fetching results......");
        dialog.show();

        search_view=findViewById(R.id.search_view);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                dialog.setTitle("Fetching results of "+s);
                dialog.show();
                RequestManager manager=new RequestManager(MainActivity.this);
                manager.getNews(listener,"general",s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        RequestManager manager=new RequestManager(this);
        manager.getNews(listener,"general",null);

    }

    private final OnFetchDataListener<NewsApiResponse> listener=new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<API_data> list, String message) {
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    public void showNews(List<API_data> list){
        rv_list=findViewById(R.id.rv_list);
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new CustomAdapter(this,list);
        rv_list.setAdapter(adapter);
        adapter.setOnItemListener(new CustomAdapter.OnItemListener() {
            @Override
            public void OnCLick(View view, int position, List<API_data> data) {
                Intent intent=new Intent(MainActivity.this,Details_Activity.class);
                intent.putExtra("data",data.get(position));
                startActivity(intent);
            }
        });
    }
}