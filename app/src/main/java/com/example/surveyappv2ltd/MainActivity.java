package com.example.surveyappv2ltd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.surveyappv2ltd.view.QuestionsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
public class MainActivity extends AppCompatActivity implements Serializable {

//    ActivityMainBinding binding;
    Button button;
//    private static ArrayList<SubmittedSurvey> submittedSurveysData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        FloatingActionButton fab = findViewById(R.id.floatingButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
                startActivityForResult(intent,1);
//                finish();
            }
        });




    }
}