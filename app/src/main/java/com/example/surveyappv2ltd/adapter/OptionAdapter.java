package com.example.surveyappv2ltd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveyappv2ltd.R;
import com.example.surveyappv2ltd.model.Options;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {

    private final Context context;
    private final List<Options> options;

    public OptionAdapter(List<Options> options, Context context) {
        this.context = context;
        this.options = options;

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
}
