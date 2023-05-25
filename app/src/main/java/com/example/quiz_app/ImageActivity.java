package com.example.quiz_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_app.adapter.ImageAdapter;
import com.example.quiz_app.dal.ImageDAO;
import com.example.quiz_app.model.Image;

import java.util.List;

public class ImageActivity extends AppCompatActivity implements ImageAdapter.ImageListener {

    private ImageAdapter adapter;
    private RecyclerView recyclerView;
    private ImageDAO mImageDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        recyclerView = findViewById(R.id.imageRecyclerView);
        adapter = new ImageAdapter(this);
        mImageDAO = new ImageDAO(this);

        if (!mImageDAO.isDataExists()) {
            mImageDAO.createInitialData();
        }

        String imageType = getIntent().getStringExtra("image-type");

        List<Image> imageList = mImageDAO.getImageByImageType(imageType);
        adapter.setLstImage(imageList);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setImageListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {

        Image image = adapter.getImage(position);
        Intent data = new Intent();

        // pass data into intent
        data.putExtra("id-image", image.getId());
        data.putExtra("src-image", image.getSrc());

        // set resultCode is Activity.RESULT_OK
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
