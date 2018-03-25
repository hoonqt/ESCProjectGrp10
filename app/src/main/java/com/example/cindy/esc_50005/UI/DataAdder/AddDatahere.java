package com.example.cindy.esc_50005.UI.DataAdder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.cindy.esc_50005.Database.Progress.NewQuizScoresDO;
import com.example.cindy.esc_50005.Database.Progress.ProgressRemoteDataSource;
import com.example.cindy.esc_50005.R;

public class AddDatahere extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_datahere);

        AWSMobileClient.getInstance().initialize(this).execute();

        ProgressRemoteDataSource scoreData = new ProgressRemoteDataSource();

        scoreData.putScores("1002210","50.001","Quiz 1",3.25);


    }
}
