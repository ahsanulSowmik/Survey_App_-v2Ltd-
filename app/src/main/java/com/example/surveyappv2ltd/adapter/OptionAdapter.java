package com.example.surveyappv2ltd.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveyappv2ltd.R;
import com.example.surveyappv2ltd.model.Options;
import com.example.surveyappv2ltd.model.SubmittedSurvey;
import com.example.surveyappv2ltd.view.QuestionsActivity;

import java.util.ArrayList;
import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {

    private final Context context;
    private final List<Options> options;
    public int questionPosition;

    private static ArrayList<SubmittedSurvey> submittedSurveysData;

    public OptionAdapter(List<Options> options, Context context, int questionPosition,ArrayList<SubmittedSurvey> submittedSurveysData) {
        this.context = context;
        this.options = options;
        this.questionPosition = questionPosition;
        this.submittedSurveysData=submittedSurveysData;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_choice_view,null  ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.answerTextView.setText(options.get(position).getValue());

       if(submittedSurveysData.get(questionPosition).getOptionPosition()==position){
          Log.d("TAG", "isCheckBoxClicked: "+submittedSurveysData.get(questionPosition).getOptionPosition()+" "+ position);
           holder.answerCheckBox.setChecked(true);
       }

        isCheckBoxClicked(holder,position);

    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox answerCheckBox;
        TextView answerTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            answerCheckBox = itemView.findViewById(R.id.answerCheckBox);
            answerTextView = itemView.findViewById(R.id.answerTextView);

        }
    }

    public void isCheckBoxClicked(ViewHolder holder, int position){
        holder.answerCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                if (checked){

                    ((QuestionsActivity)context).setAnswer(questionPosition,options.get(position).getValue(),position);

                }
            }
        });
    }
}
