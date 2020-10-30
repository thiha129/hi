package com.example.test1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter_Web extends RecyclerView.Adapter<MainAdapter_Web.ViewHolder> {

    ArrayList<MainModel_web> mainModel_webs;
    Context context;

    public MainAdapter_Web(Context context, ArrayList<MainModel_web> mainModel_webs) {
        this.mainModel_webs = mainModel_webs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_web, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.imageView_web.setImageResource(mainModel_webs.get(position).getLangLogo());
        holder.textView_web_1.setText(mainModel_webs.get(position).getLangName());
        holder.textView_web_2.setText(mainModel_webs.get(position).getLanglink());
        holder.cardView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetalActivity.class);
                intent.putExtra("link", mainModel_webs.get(position).getLanglink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainModel_webs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_web;
        TextView textView_web_1;
        TextView textView_web_2;
        CardView cardView_1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_web = itemView.findViewById(R.id.image_web);
            textView_web_1 = itemView.findViewById(R.id.text_web);
            textView_web_2 = itemView.findViewById(R.id.linkWeb);
            cardView_1 = itemView.findViewById(R.id.haha_1);
        }
    }
}
