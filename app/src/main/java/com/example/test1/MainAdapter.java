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

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<MainModel> mainModels;
    Context context;

    public MainAdapter(Context context, ArrayList<MainModel> mainModels){
        this.context = context;
        this.mainModels = mainModels;
        
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       holder.imageView.setImageResource(mainModels.get(position).getLangLogo());
       holder.textView.setText(mainModels.get(position).getLangName());
       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(context, DetalActivity.class);
               intent.putExtra("logo", mainModels.get(position).getLangLogo());
               intent.putExtra("name", mainModels.get(position).getLangName());
            context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView =  itemView.findViewById(R.id.text_view);
            cardView= itemView.findViewById(R.id.haha);
        }
    }

}
