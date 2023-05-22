package com.example.quiz_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quiz_app.R;
import com.example.quiz_app.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> lstCate;
    private CategoryListener categoryListener;

    public CategoryAdapter(Context context) {
        this.context = context;
        lstCate = new ArrayList<>();
    }

    public void setCategoryListener(CategoryListener categoryListener) {
        this.categoryListener = categoryListener;
    }

    public void setLstCate(List<Category> lstCate) {
        this.lstCate = lstCate;
        notifyDataSetChanged();
    }

    public Category getCate(int position) {
        return lstCate.get(position);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        Category category = lstCate.get(position);
        holder.cateName.setText(category.getName());
        Glide.with(context)
                .load(category.getImg())
                .into(holder.cateImage);
    }

    @Override
    public int getItemCount() {
        return lstCate.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView cateName;
        private ImageView cateImage;

        public CategoryViewHolder(@NonNull View view) {
            super(view);

            cateName = view.findViewById(R.id.category);
            cateImage = view.findViewById(R.id.image);
        }

        @Override
        public void onClick(View view) {
            if(categoryListener != null) {
                categoryListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface CategoryListener {

        void onItemClick (View view, int position);
    }
}
