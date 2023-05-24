package com.example.quiz_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_app.R;
import com.example.quiz_app.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private Context context;
    private List<Quiz> lstQuiz;
    private QuizListener quizListener;

    public QuizAdapter(Context context) {
        this.context = context;
        lstQuiz = new ArrayList<>();
    }

    public void setQuizListener(QuizListener quizListener) {
        this.quizListener = quizListener;
    }

    public void setLstQuiz(List<Quiz> lstQuiz) {
        this.lstQuiz = lstQuiz;
        notifyDataSetChanged();
    }

    public Quiz getQuiz(int position) {
        return lstQuiz.get(position);
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false);

        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {

        Quiz quiz = lstQuiz.get(position);
        holder.question.setText(quiz.getQuizQuestion());
        holder.as1.setText(quiz.getAnswers().get(0).getContent());
        holder.as2.setText(quiz.getAnswers().get(1).getContent());
        holder.as3.setText(quiz.getAnswers().get(2).getContent());
        holder.as4.setText(quiz.getAnswers().get(3).getContent());
        if (quiz.getAnswers().get(0).getTrue()) {
            holder.q1.setImageResource(R.drawable.baseline_radio_button_checked_24);
        }
        if (quiz.getAnswers().get(1).getTrue()) {
            holder.q2.setImageResource(R.drawable.baseline_radio_button_checked_24);
        }
        if (quiz.getAnswers().get(2).getTrue()) {
            holder.q3.setImageResource(R.drawable.baseline_radio_button_checked_24);
        }
        if (quiz.getAnswers().get(3).getTrue()) {
            holder.q4.setImageResource(R.drawable.baseline_radio_button_checked_24);
        }
    }

    @Override
    public int getItemCount() {
        return lstQuiz.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView question, as1, as2, as3, as4;
        private ImageView q1, q2, q3, q4;

        public QuizViewHolder(@NonNull View view) {
            super(view);
            question = view.findViewById(R.id.showQuizQuestion);
            as1 = view.findViewById(R.id.showAnswerA);
            as2 = view.findViewById(R.id.showAnswerB);
            as3 = view.findViewById(R.id.showAnswerC);
            as4 = view.findViewById(R.id.showAnswerD);
            q1 = view.findViewById(R.id.radioAnswerA);
            q2 = view.findViewById(R.id.radioAnswerB);
            q3 = view.findViewById(R.id.radioAnswerC);
            q4 = view.findViewById(R.id.radioAnswerD);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(quizListener != null) {
                quizListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface QuizListener {

        void onItemClick (View view, int position);
    }
}
