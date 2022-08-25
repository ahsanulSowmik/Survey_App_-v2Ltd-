package com.example.surveyappv2ltd.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.surveyappv2ltd.R;
import com.example.surveyappv2ltd.adapter.QuestionAdapter;
import com.example.surveyappv2ltd.model.Questions;
import com.example.surveyappv2ltd.retrofit.RetrofitRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsActivity<layoutManager> extends AppCompatActivity {
    private static final String TAG = QuestionsActivity.class.getSimpleName();

    private RecyclerView recycler_view;
    private ProgressBar progress_bar;

    private LinearLayoutManager layoutManager;
    public static  List<Questions> questionsList;


    private QuestionAdapter questionRecyclerViewAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

//        superListView = findViewById(R.id.superListView);

        Log.d(TAG, "onCreate: ");



        getQuestions();

    }




    private void getQuestions() {
        Call<List<Questions>> call = RetrofitRequest.getInstance().getQuestionApi().getQuestions();
        call.enqueue(new Callback<List<Questions>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {

                questionsList = response.body();
                setQuestionRecyclerView(questionsList);

            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });

    }

    private void setQuestionRecyclerView(List<Questions> questionArrayList) {

        recycler_view = findViewById(R.id.recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(QuestionsActivity.this);
        recycler_view.setLayoutManager(layoutManager);

        recycler_view.setHasFixedSize(true);

        // adapter
        questionRecyclerViewAdapter = new QuestionAdapter(QuestionsActivity.this, questionArrayList);
//        Log.d(TAG, "befor setadapter"+ questionArrayList.get(0));
        recycler_view.setAdapter(questionRecyclerViewAdapter);
//        Log.d(TAG, "after setadapter"+ String.valueOf(questionArrayList.get(0).getOptions().get(1).getValue()));


    }
}