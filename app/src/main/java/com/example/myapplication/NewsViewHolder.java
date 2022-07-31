package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    TextView text_title, text_source;
    ImageView img_news;
    CardView cardView;


    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);

        text_title = itemView.findViewById(R.id.text_title);
        text_source = itemView.findViewById(R.id .text_source);
        img_news = itemView.findViewById(R.id.img_news);
        cardView = itemView.findViewById(R.id.news_container);
    }
}
