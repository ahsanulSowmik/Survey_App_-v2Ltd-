package com.example.surveyappv2ltd.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surveyappv2ltd.R;
import com.example.surveyappv2ltd.model.Questions;
import com.example.surveyappv2ltd.model.SubmittedSurvey;
import com.example.surveyappv2ltd.view.QuestionsActivity;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter<QuestionsAdapter> extends RecyclerView.Adapter <QuestionAdapter.ViewHolder> {

    private final Context context;
    private List<Questions> questionArrayList;

    private LinearLayoutManager layoutManager;

    private static ArrayList<SubmittedSurvey> submittedSurveysData;

    private OptionAdapter optionAdapter;
    Bitmap photo;


    boolean isOnTextChanged = false;
//    private MyInterface listener;


    public QuestionAdapter(Context context, List<Questions> questionArrayList, Bitmap photo, ArrayList<SubmittedSurvey> submittedSurveysData) {
        this.context = context;
        this.questionArrayList = questionArrayList;
        this.photo =  photo;
        this.submittedSurveysData = submittedSurveysData;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_each_questions,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


       viewHolder. question_text.setText(questionArrayList.get(i).getQuestion());

       if(questionArrayList.get(i).getRequired()){
           viewHolder.requiredView.setVisibility(View.VISIBLE);
       }

       String questionType = questionArrayList.get(i).getType();
        if(questionType.equals("multipleChoice") || questionType.equals("checkbox")){

            viewHolder.multipleAnsRecycleView.setVisibility(View.VISIBLE);
            OptionAdapter questionAdapter;
            questionAdapter = new OptionAdapter(questionArrayList.get(i).getOptions(),context,i,submittedSurveysData);
            viewHolder.multipleAnsRecycleView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            viewHolder.multipleAnsRecycleView.setAdapter(questionAdapter);
            questionAdapter.notifyDataSetChanged();

        }
        else if(questionType.equals("textInput")){
            viewHolder.textInputView.setVisibility(View.VISIBLE);
            viewHolder.textInputView.setText(submittedSurveysData.get(i).getAnswer());
            editText(viewHolder,i);
        }
        else if(questionType.equals("numberInput")){
            viewHolder.textInputView.setVisibility(View.VISIBLE);
            viewHolder.textInputView.setInputType(InputType.TYPE_CLASS_NUMBER);
            viewHolder.textInputView.setText(submittedSurveysData.get(i).getAnswer());
            editText(viewHolder,i);
        }

        else if (questionType.equals("dropdown")){

            viewHolder.dropdown.setVisibility(View.VISIBLE);
            setOption(viewHolder,i);

        }
        else if (questionType.equals("camera")){

            if(photo == null){

                viewHolder.cameraView.setVisibility(View.VISIBLE);
                viewHolder.cameraView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
//                        String  value = viewHolder.numberInputView.getText().toString();
//                        Log.d("TAG", "numberInputView: "+value);
                           ((QuestionsActivity)context).captureImage(12,"sowmik");

                    }
                });

            }
            else{
                viewHolder.cameraView.setVisibility(View.GONE);
                Log.d("TAG", "onClick photo: "+photo);
                viewHolder.imageview.setVisibility(View.VISIBLE);
                viewHolder.imageview.setImageBitmap(photo);
            }

        }

    }

    public void editText(ViewHolder viewHolder, int position){
//        viewHolder.textInputView.setVisibility(View.VISIBLE);

        viewHolder.textInputView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("TAG", "check beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChanged = true;
                Log.d("TAG", "check onTextChanged: ");

            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d("TAG", "check afterTextChanged: "+s);

                submittedSurveysData.get(position).setAnswer(s.toString());

            }

        });


    }


    public void setOption(ViewHolder viewHolder, int i){
        ArrayList<String> arrayList = new ArrayList<>();
//            arrayList.add(questionArrayList.get(0).getOptions().get(0).getValue());


        for(int optionPosition = 0 ;optionPosition<questionArrayList.get(i).getOptions().size();optionPosition++){
            arrayList.add(questionArrayList.get(i).getOptions().get(optionPosition).getValue());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewHolder.dropdown.setAdapter(arrayAdapter);
        viewHolder.dropdown.setSelection(submittedSurveysData.get(i).getOptionPosition());
        viewHolder.dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String dropdownSelectedAnswer = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + position,Toast.LENGTH_LONG).show();

                    submittedSurveysData.get(i).setOptionPosition(position);
                    submittedSurveysData.get(i).setAnswer(dropdownSelectedAnswer);

            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {


            }
        });
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
        public TextView requiredView;
        ImageButton cameraView;
        ImageView imageview;
        public Button button;
//        private final TextView question_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question_text = itemView.findViewById(R.id.question_text);
            multipleAnsRecycleView = itemView.findViewById(R.id.answerRecyclerView);
            textInputView = itemView.findViewById(R.id.textInputView);
            numberInputView = itemView.findViewById(R.id.numberInputView);
            cameraView = itemView.findViewById(R.id.cameraView);
            imageview = itemView.findViewById(R.id.imageview);

            dropdown = itemView.findViewById(R.id.dropdown);

            requiredView = itemView.findViewById(R.id.requiredView);


            String value;
            value = textInputView.getText().toString();
            Log.d("TAG", "ViewHolder: "+value);

            // attaching data adapter to spinner
//            dropdown.setAdapter(categories);
        }
    }



}