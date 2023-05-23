package com.example.quiz_app.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.quiz_app.fragment.CreateActivityFragment;
import com.example.quiz_app.fragment.DoingActivityFragment;
import com.example.quiz_app.fragment.DoneActivityFragment;

public class FragmentActivityAdapter extends FragmentPagerAdapter {

    private int pageNumber;

    public FragmentActivityAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNumber = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DoingActivityFragment doingActivityFragment = new DoingActivityFragment();
                return doingActivityFragment;
            case 1:
                DoneActivityFragment doneActivityFragment = new DoneActivityFragment();
                return doneActivityFragment;
            case 2:
                CreateActivityFragment createActivityFragment = new CreateActivityFragment();
                return createActivityFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageNumber;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Doing";
            case 1: return "Done";
            case 2: return "Create";
        }
        return null;
    }
}
