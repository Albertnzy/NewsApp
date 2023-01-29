package com.example.yournews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yournews.R;
import com.example.yournews.models.API_data;
import com.example.yournews.models.NewsApiResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    Context ctx;
    List<API_data> news_data;
    public OnItemListener onItemListener;

    public CustomAdapter(Context context,List<API_data> data){
        this.ctx=context;
        this.news_data=data;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(ctx).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.txt_Title.setText(news_data.get(position).getTitle());
        holder.txt_Source.setText(news_data.get(position).getSource().getName());

        if(news_data.get(position).getUrlToImage()!=null){
            Picasso.get().load(news_data.get(position).getUrlToImage()).into(holder.img_Source);
        }
        holder.bind(news_data.get(position));
    }

    @Override
    public int getItemCount() {
        return news_data.size();
    }


     class CustomViewHolder extends RecyclerView.ViewHolder{

    TextView txt_Title,txt_Source;
    ImageView img_Source;
    CardView cardView;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_Title=itemView.findViewById(R.id.txt_title);
        txt_Source=itemView.findViewById(R.id.txt_Source);
        img_Source=itemView.findViewById(R.id.img_news);
        cardView=itemView.findViewById(R.id.main_container);
    }

    void bind(API_data data){
       itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                onItemListener.OnCLick(view,getAdapterPosition(),news_data);
           }
       });
    }
    }
    public interface OnItemListener {

        void OnCLick(View view, int position, List<API_data> data);
    }
    public void setOnItemListener(OnItemListener listener){
        this.onItemListener=listener;
    }

    }

