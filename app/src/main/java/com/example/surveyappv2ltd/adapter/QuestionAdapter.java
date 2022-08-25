package com.example.surveyappv2ltd.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveyappv2ltd.R;
import com.example.surveyappv2ltd.model.Questions;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter<QuestionsAdapter> extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private final Context context;
    private List<Questions> questionArrayList;

    private LinearLayoutManager layoutManager;

    private OptionAdapter optionAdapter;


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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

//        String[] questionType = {"multipleChoice","textInput","dropdown","checkbox","numberInput","camera"};

//        Log.d("TAG", "questionType: "+questionType[0]);

       Log.d("TAG", "onBindViewHolder: "+ questionArrayList.get(i).getType());
       viewHolder. question_text.setText(questionArrayList.get(i).getQuestion());



       String questionType = questionArrayList.get(i).getType();



        if(questionType.equals("multipleChoice")){

            viewHolder.multipleAnsRecycleView.setVisibility(View.VISIBLE);
            OptionAdapter questionAdapter;
            questionAdapter = new OptionAdapter(questionArrayList.get(0).getOptions(),context);
            viewHolder.multipleAnsRecycleView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            viewHolder.multipleAnsRecycleView.setAdapter(questionAdapter);
            questionAdapter.notifyDataSetChanged();

        }
        else if(questionType.equals("textInput")){
            viewHolder.textInputView.setVisibility(View.VISIBLE);

        }
        else if(questionType.equals("numberInput")){
            viewHolder.numberInputView.setVisibility(View.VISIBLE);

        }

//        questionArrayList.get(i)

//        ArrayAdapter<Questions> adapter = new ArrayAdapter<Questions>(this, android.R.layout.simple_spinner_dropdown_item, questionArrayList);
////set the spinners adapter to the previously created one.
//        viewHolder.dropdown.setAdapter(adapter);







    }



    @Override
    public int getItemCount() {
//        Log.d("TAG", "getItemCount: "+questionArrayList.size());
        return questionArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView question_text;
        public RecyclerView multipleAnsRecycleView;
        public EditText textInputView;
        public EditText numberInputView;
        public Spinner dropdown;
//        private final TextView question_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question_text = itemView.findViewById(R.id.question_text);
            multipleAnsRecycleView = itemView.findViewById(R.id.answerRecyclerView);
            textInputView = itemView.findViewById(R.id.textInputView);
            numberInputView = itemView.findViewById(R.id.numberInputView);

            dropdown = itemView.findViewById(R.id.dropdown);

            // attaching data adapter to spinner
//            dropdown.setAdapter(categories);
        }
    }



}