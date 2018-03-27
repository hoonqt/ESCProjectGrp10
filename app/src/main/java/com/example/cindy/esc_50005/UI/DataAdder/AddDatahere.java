package com.example.cindy.esc_50005.UI.DataAdder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.cindy.esc_50005.Database.Progress.NewQuizScoresDO;
import com.example.cindy.esc_50005.Database.Progress.ProgressRemoteDataSource;
import com.example.cindy.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.cindy.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.cindy.esc_50005.R;

import java.util.ArrayList;

public class AddDatahere extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_datahere);

        AWSMobileClient.getInstance().initialize(this).execute();

        QuizRemoteDataSource data = new QuizRemoteDataSource();

        ArrayList<QuizQuestions1DO> questions = data.getQuestions("aabc","");
        Log.i("Mcgahn",questions.get(0).getQuestion());

    }
}
