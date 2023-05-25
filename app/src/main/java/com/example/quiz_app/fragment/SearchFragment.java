package com.example.quiz_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_app.QuizActivity;
import com.example.quiz_app.R;
import com.example.quiz_app.adapter.LearningObjectAdapter;
import com.example.quiz_app.dal.LearningObjectDAO;
import com.example.quiz_app.model.LearningObject;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements LearningObjectAdapter.LearningObjectListener,
        SearchView.OnQueryTextListener {

    private LearningObjectAdapter adapter;
    private RecyclerView recyclerView;
    private LearningObjectDAO mLearningObjectDAO;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.searchRecyclerView);
        searchView = view.findViewById(R.id.search);
        adapter = new LearningObjectAdapter(getContext());
        mLearningObjectDAO = new LearningObjectDAO(getContext());

        List<LearningObject> learningObjects = mLearningObjectDAO.getAllLearningObjects();
        adapter.setLstLearningObject(learningObjects);
        adapter.setBackup(learningObjects);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setLearningObjectListener(this);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intent = new Intent(getActivity(), QuizActivity.class);
        intent.putExtra("lo-id", adapter.getLearningObject(position).getId());
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        filter(s);
        return false;
    }

    private void filter(String s) {
        List<LearningObject> filterLst = new ArrayList<>();
        for (LearningObject i : adapter.getBackup()) {
            if(i.getTitle().toLowerCase().contains(s.toLowerCase())) {
                filterLst.add(i);
            }
        }
        if(filterLst.isEmpty()) {
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        }
        else {
            adapter.filterList(filterLst);
        }
    }

}
