package com.example.surveyappv2ltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.surveyappv2ltd.databinding.ActivityMainBinding;
import com.example.surveyappv2ltd.view.QuestionsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
//
//                startActivity(intent);
////                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
////                     .setAction("Action", null).show();
//
//            }
//        });

        FloatingActionButton fab = findViewById(R.id.floatingButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }
}