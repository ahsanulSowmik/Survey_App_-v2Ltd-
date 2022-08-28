package com.example.surveyappv2ltd.view;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.surveyappv2ltd.R;
import com.example.surveyappv2ltd.adapter.QuestionAdapter;
import com.example.surveyappv2ltd.model.Questions;
import com.example.surveyappv2ltd.model.SubmittedSurvey;
import com.example.surveyappv2ltd.retrofit.RetrofitRequest;

import java.io.ByteArrayOutputStream;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsActivity<layoutManager> extends AppCompatActivity {
    private static final String TAG = QuestionsActivity.class.getSimpleName();

    private RecyclerView recycler_view;
    Bitmap photo;

    Button submitButton;
    ProgressBar progress_bar;

    public HashMap<Integer,String> userSubmittedAnswer = new HashMap<Integer,String>();;

    String imageTempName;
    int position;

    private LinearLayoutManager layoutManager;
    public static  List<Questions> questionsList;


    SubmittedSurvey submittedSurveys;
    private static ArrayList<SubmittedSurvey> submittedSurveysData;
    private QuestionAdapter questionRecyclerViewAdapter;

    public String imageUrl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        progress_bar = findViewById(R.id.progress_bar);
        submitButton = findViewById(R.id.button);

        Log.d(TAG, "onCreate: ");
        getQuestions();

       submitAnswers();

    }




    private void getQuestions() {
        Call<List<Questions>> call = RetrofitRequest.getInstance().getQuestionApi().getQuestions();
        call.enqueue(new Callback<List<Questions>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {

                questionsList = response.body();

                addAnswerInArray();

                progress_bar.setVisibility(View.GONE);
                setQuestionRecyclerView(questionsList);
               submitButton.setVisibility(View.VISIBLE);

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
        questionRecyclerViewAdapter = new QuestionAdapter(QuestionsActivity.this, questionArrayList,photo,submittedSurveysData);
        recycler_view.setAdapter(questionRecyclerViewAdapter);
//        Log.d(TAG, "after setadapter"+ String.valueOf(questionArrayList.get(0).getOptions().get(1).getValue()));


    }

    public void addAnswerInArray(){
        submittedSurveysData = new ArrayList<>();

        // generate ArrayList and store in data model
        for(int i =0;i<questionsList.size();i++){
            submittedSurveys = new SubmittedSurvey(
                    i,
                    -1,
                    questionsList.get(i).getQuestion(),
                    null

            );
            submittedSurveysData.add(submittedSurveys);
        }
        Log.d(TAG, "addAnswerInArray: "+submittedSurveysData.size());
    }

    public void captureImage(int pos, String imageName) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(cameraIntent, 123);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photo = (Bitmap) data.getExtras().get("data");
//        imageView.setImageBitmap(photo);

         imageUrl = BitMapToString(photo);
//        submittedSurveysData.get(pos).setAnswer(imageUrl);

        Log.d(TAG, "captureImage onActivityResult: "+imageUrl );
        setQuestionRecyclerView(questionsList);
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public void submitAnswers(){
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<submittedSurveysData.size();i++){
                    if(questionsList.get(i).getType().equals("camera")){
                        submittedSurveysData.get(i).setAnswer(imageUrl);
                    }
                    Log.d(TAG, "allAnswer: "+submittedSurveysData.get(i).getQuestion()+" "+submittedSurveysData.get(i).getAnswer());
                }

            }
        });
    }


    public void setAnswer(int questionPosition, String value, int position) {

        submittedSurveysData.get(questionPosition).setOptionPosition(position);
        submittedSurveysData.get(questionPosition).setAnswer(value);
        Log.d(TAG, "fromOption: "+submittedSurveysData.get(questionPosition).getAnswer());

    }

}