package com.ministore.crafie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ministore.crafie.Domain.Category;
import com.ministore.crafie.ItemActivity;
import com.ministore.crafie.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private  Context context;
    private List<Category> mCategorylist;
    public CategoryAdapter(Context context, List<Category> mCategorylist) {
        this.context=context;
        this.mCategorylist=mCategorylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.single_category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(mCategorylist.get(position).getImg_url()).into(holder.mTypeimg);
        holder.mTypeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, ItemActivity.class);
                intent.putExtra("type",mCategorylist.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategorylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mTypeimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTypeimg=itemView.findViewById(R.id.category_img);
        }
    }
}
