package com.example.quiz_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quiz_app.R;
import com.example.quiz_app.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private List<Image> lstImage;
    private ImageListener imageListener;

    public ImageAdapter(Context context) {
        this.context = context;
        lstImage = new ArrayList<>();
    }

    public void setImageListener(ImageListener imageListener) {
        this.imageListener = imageListener;
    }

    public void setLstImage(List<Image> lstImage) {
        this.lstImage = lstImage;
        notifyDataSetChanged();
    }

    public Image getImage(int position) {
        return lstImage.get(position);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageViewHolder holder, int position) {

        Image image = lstImage.get(position);
        Glide.with(context)
                .load(image.getSrc())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return lstImage.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView img;

        public ImageViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.imageItem);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(imageListener != null) {
                imageListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ImageListener {

        void onItemClick (View view, int position);
    }
}
