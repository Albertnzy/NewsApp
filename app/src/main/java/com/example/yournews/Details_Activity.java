package com.example.yournews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yournews.models.API_data;
import com.squareup.picasso.Picasso;

public class Details_Activity extends AppCompatActivity {

    API_data data;
    TextView txt_title,txt_author,txt_time,txt_detail,txt_content;
    ImageView img_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt_title=findViewById(R.id.txt_detail_title);
        txt_author=findViewById(R.id.txt_detail_author);
        txt_time=findViewById(R.id.txt_detail_time);
        txt_detail=findViewById(R.id.text_detail_detail);
        txt_content=findViewById(R.id.text_detail_content);
        img_news=findViewById(R.id.img_details_news);

        data=(API_data) getIntent().getSerializableExtra("data");
        txt_title.setText(data.getTitle());
        txt_author.setText(data.getAuthor());
        txt_time.setText(data.getPublishedAt());
        txt_detail.setText(data.getDescription());
        txt_content.setText(data.getContent());

        if ((data.urlToImage!=null)){
            Picasso.get().load(data.getUrlToImage()).into(img_news);
        }else {
           img_news.setVisibility(View.GONE);
        }


    }
}