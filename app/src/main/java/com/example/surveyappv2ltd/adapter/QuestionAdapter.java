package com.example.surveyappv2ltd.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.surveyappv2ltd.R;
import com.example.surveyappv2ltd.model.Questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private final Context context;
    private List<Questions> questionArrayList;

    public QuestionAdapter(Context context, List<Questions> questionArrayList) {
        this.context = context;
        this.questionArrayList = questionArrayList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_each_questions,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder viewHolder, int i) {

//       Log.d("TAG", "onBindViewHolder: "+ String.valueOf(questionArrayList.get(i).getQuestion()));
       viewHolder. tvTitle.setText(questionArrayList.get(i).getQuestion());

    }


    @Override
    public int getItemCount() {
//        Log.d("TAG", "getItemCount: "+questionArrayList.size());
        return questionArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.question_text);

            Log.d("TAG", "ViewHolder: ");
        }
    }
}