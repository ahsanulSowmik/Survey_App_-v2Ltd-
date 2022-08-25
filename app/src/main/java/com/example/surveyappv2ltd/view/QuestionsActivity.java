package com.example.surveyappv2ltd.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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
    private ArrayList<Questions> questionArrayList = new ArrayList<>();


    private QuestionAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        superListView = findViewById(R.id.superListView);

        getQuestions();
    }

    private void getQuestions() {
        Call<List<Questions>> call = RetrofitRequest.getInstance().getQuestionApi().getQuestions();
        call.enqueue(new Callback<List<Questions>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                List<Questions> myheroList = response.body();
                String[] oneHeroes = new String[myheroList.size()];

//                for (int i = 0; i < myheroList.size(); i++) {
//                    oneHeroes[i] = String.valueOf(myheroList.get(0).getOptions().get(1).getValue());
//                }

                Log.d(TAG, "onResponse: "+String.valueOf(myheroList.get(0).getOptions().get(1).getValue()));

//                superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }
}