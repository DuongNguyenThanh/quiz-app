package com.example.quiz_app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

    public void addQuiz(Quiz quiz) {
        lstQuiz.add(quiz);
        notifyDataSetChanged();
    }

    public void updateQuiz(int position, Quiz quiz) {
        lstQuiz.set(position, quiz);
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
        holder.numQuiz.setText("Quiz: " + (position+1));
        holder.question.setText(quiz.getQuizQuestion());

        // Set text
        holder.as1.setText(quiz.getAnswers().get(0).getContent());
        holder.as2.setText(quiz.getAnswers().get(1).getContent());
        holder.as3.setText(quiz.getAnswers().get(2).getContent());
        holder.as4.setText(quiz.getAnswers().get(3).getContent());

        // Set color
        holder.as1.setBackgroundResource(R.drawable.custom_edittext_red);
        holder.as2.setBackgroundResource(R.drawable.custom_edittext_red);
        holder.as3.setBackgroundResource(R.drawable.custom_edittext_red);
        holder.as4.setBackgroundResource(R.drawable.custom_edittext_red);
        if (quiz.getAnswers().get(0).getTrue() == 1) {
            holder.as1.setBackgroundResource(R.drawable.custom_edittext_green);
        }
        if (quiz.getAnswers().get(1).getTrue() == 1) {
            holder.as2.setBackgroundResource(R.drawable.custom_edittext_green);
        }
        if (quiz.getAnswers().get(2).getTrue() == 1) {
            holder.as3.setBackgroundResource(R.drawable.custom_edittext_green);
        }
        if (quiz.getAnswers().get(3).getTrue() == 1) {
            holder.as4.setBackgroundResource(R.drawable.custom_edittext_green);
        }

        holder.delQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Warning");
                builder.setIcon(R.drawable.remove);
                builder.setMessage("Are you sure to delete quiz " + (holder.getAdapterPosition()+1));
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        lstQuiz.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstQuiz.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView numQuiz, question, as1, as2, as3, as4;
        private ImageView delQuiz;

        public QuizViewHolder(@NonNull View view) {
            super(view);
            numQuiz = view.findViewById(R.id.numQuiz);
            question = view.findViewById(R.id.showQuizQuestion);
            as1 = view.findViewById(R.id.showAnswerA);
            as2 = view.findViewById(R.id.showAnswerB);
            as3 = view.findViewById(R.id.showAnswerC);
            as4 = view.findViewById(R.id.showAnswerD);
            delQuiz = view.findViewById(R.id.delQuiz);

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
