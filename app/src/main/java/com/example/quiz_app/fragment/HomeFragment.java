package com.example.quiz_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_app.R;
import com.example.quiz_app.ShowQuizActivity;
import com.example.quiz_app.adapter.CategoryAdapter;
import com.example.quiz_app.dal.CategoryDAO;
import com.example.quiz_app.model.Category;

import java.util.List;

public class HomeFragment extends Fragment implements CategoryAdapter.CategoryListener {

    private CategoryAdapter adapter;
    private RecyclerView recyclerView;
    private CategoryDAO categoryDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.categoryRecyclerView);
        adapter = new CategoryAdapter(getContext());
        categoryDAO = new CategoryDAO(getContext());

        // Check data table category
        if (!categoryDAO.isDataExists()) {
            categoryDAO.createInitialData();
        }

        List<Category> categoryList = categoryDAO.getAllCategories();
        adapter.setLstCate(categoryList);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setCategoryListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {

        Category category = adapter.getCate(position);
        Intent intent = new Intent(getActivity(), ShowQuizActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
