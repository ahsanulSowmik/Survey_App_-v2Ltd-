package com.example.surveyappv2ltd.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveyappv2ltd.R;
import com.example.surveyappv2ltd.view.SurveyHistory;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter {


    SurveyHistory surveyHistory;
    private ArrayList<String> ques, ans;

    public HistoryAdapter(SurveyHistory surveyHistory, ArrayList<String> ques, ArrayList<String> ans) {

        this.surveyHistory = surveyHistory;
        this.ques = ques;
        this.ans = ans;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String getQues(int position) {
        return ques.get(position);
    }

    @Override
    public int getItemCount() {
        return ques.size();
    }

}
