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
import com.example.quiz_app.dal.UserDAO;
import com.example.quiz_app.model.LearningObject;
import com.example.quiz_app.model.User;
import com.example.quiz_app.model.enumtype.UserLoStatusEnum;

import java.util.ArrayList;
import java.util.List;

public class LearningObjectAdapter extends RecyclerView.Adapter<LearningObjectAdapter.LearningObjectViewHolder> {

    private Context context;
    private List<LearningObject> lstLearningObject;
    private List<LearningObject> lstBackup;
    private LearningObjectListener learningObjectListener;
    private UserDAO mUserDAO;

    public LearningObjectAdapter(Context context) {
        this.context = context;
        lstLearningObject = new ArrayList<>();
        lstBackup = new ArrayList<>();
    }

    public List<LearningObject> getBackup() {

        return lstBackup;
    }

    public void setBackup(List<LearningObject> lstBackup) {

        this.lstBackup = lstBackup;
    }

    public void setLearningObjectListener(LearningObjectListener learningObjectListener) {
        this.learningObjectListener = learningObjectListener;
    }

    public void filterList(List<LearningObject> filterLst) {

        lstLearningObject = filterLst;
        notifyDataSetChanged();
    }

    public void setLstLearningObject(List<LearningObject> lstLearningObject) {
        this.lstLearningObject = lstLearningObject;
        notifyDataSetChanged();
    }

    public void addLearningObject(LearningObject learningObject) {
        lstLearningObject.add(learningObject);
        notifyDataSetChanged();
    }

    public LearningObject getLearningObject(int position) {
        return lstLearningObject.get(position);
    }

    @NonNull
    @Override
    public LearningObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.learning_object_item, parent, false);

        mUserDAO = new UserDAO(context);

        return new LearningObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningObjectViewHolder holder, int position) {

        LearningObject learningObject = lstLearningObject.get(position);
        holder.nameLo.setText(learningObject.getTitle());
        holder.categoryLo.setText(learningObject.getCategory().getName());
        if (learningObject.getImage().getId() != -1) {
            Glide.with(context)
                    .load(learningObject.getImage().getSrc())
                    .into(holder.imageLo);
        }
        else {
            holder.imageLo.setImageResource(R.drawable.avt_profile);
        }
        User user = mUserDAO.getUserByLoIdAndStatus(learningObject.getId(), UserLoStatusEnum.CREATE_LO.name());
        holder.numQuestion.setText("Number of questions: " + learningObject.getQuizzes().size());
        holder.createdBy.setText("Created by: " + user.getName());
    }

    @Override
    public int getItemCount() {
        return lstLearningObject.size();
    }

    public class LearningObjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameLo, numQuestion, createdBy, categoryLo;
        private ImageView imageLo;

        public LearningObjectViewHolder(@NonNull View view) {
            super(view);
            nameLo = view.findViewById(R.id.nameLo);
            numQuestion = view.findViewById(R.id.numQuestion);
            createdBy = view.findViewById(R.id.createdBy);
            categoryLo = view.findViewById(R.id.categoryLo);
            imageLo = view.findViewById(R.id.imageLo);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(learningObjectListener != null) {
                learningObjectListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface LearningObjectListener {

        void onItemClick (View view, int position);
    }
}
