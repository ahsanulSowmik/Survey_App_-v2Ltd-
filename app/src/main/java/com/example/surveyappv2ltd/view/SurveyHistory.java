package com.example.surveyappv2ltd.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.surveyappv2ltd.Database.DBHelper;
import com.example.surveyappv2ltd.R;
import com.example.surveyappv2ltd.adapter.HistoryAdapter;

import java.util.ArrayList;

public class SurveyHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> ques, ans;
    DBHelper DB;
    HistoryAdapter historyAdapter;
    CardView underConstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_history);

        DB = new DBHelper(this);
        ques = new ArrayList<>();
        ans = new ArrayList<>();

//        recyclerView = findViewById(R.id.surveyHistory);
//        historyAdapter = new HistoryAdapter(this, ques,ans);
//        recyclerView.setAdapter(historyAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        displaydata();

        underConstruction = findViewById(R.id.underConstruction);
        underConstruction.setVisibility(View.VISIBLE);

    }

    private void displaydata()
    {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(SurveyHistory.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                ques.add(cursor.getString(0));
                ans.add(cursor.getString(1));
            }
        }
    }
}