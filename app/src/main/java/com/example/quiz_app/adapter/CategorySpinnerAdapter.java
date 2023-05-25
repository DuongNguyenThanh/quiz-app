package com.example.quiz_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quiz_app.R;
import com.example.quiz_app.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategorySpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<Category> lstCategory;

    public CategorySpinnerAdapter(Context context) {
        this.context = context;
        lstCategory = new ArrayList<>();
    }

    public void setLstCategory(List<Category> lstCategory) {
        this.lstCategory = lstCategory;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lstCategory.size();
    }

    @Override
    public Object getItem(int pos) {
        return lstCategory.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        View item = LayoutInflater.from(context).inflate(R.layout.spinner_item_text, viewGroup, false);
        TextView tv = item.findViewById(R.id.cateTv);
        tv.setText(lstCategory.get(pos).getName());
        return item;
    }
}
