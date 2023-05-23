package com.example.quiz_app.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.quiz_app.CreateLoActivity;
import com.example.quiz_app.LoginActivity;
import com.example.quiz_app.R;
import com.example.quiz_app.RegisterActivity;
import com.example.quiz_app.adapter.FragmentActivityAdapter;
import com.google.android.material.tabs.TabLayout;

public class ActivityFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button btnCreateQuiz;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewPageActivity);
        tabLayout = view.findViewById(R.id.tabLayout);
        btnCreateQuiz = view.findViewById(R.id.btnAddQuiz);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentActivityAdapter adapter = new FragmentActivityAdapter(manager, 3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTabLayoutTitleColor();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.purple));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnCreateQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),
                        CreateLoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setTabLayoutTitleColor() {
        if (viewPager.getCurrentItem() == 0) {
            tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.purple));
        }
    }
}
