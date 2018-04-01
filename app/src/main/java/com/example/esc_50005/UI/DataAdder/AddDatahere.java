package com.example.esc_50005.UI.DataAdder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.esc_50005.Database.Progress.ProgressRemoteDataSource;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.R;

import java.util.ArrayList;

public class AddDatahere extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_datahere);

        AWSMobileClient.getInstance().initialize(this).execute();

        ProgressRemoteDataSource data = new ProgressRemoteDataSource();

        data.putScores("1002212","50.004","Session1","Quiz 1",3.0,"Alvin Chang");
        data.putScores("1002212","50.004","Session2","Quiz 1",4.0,"Alvin Chang");
        data.putScores("1002212","50.004","Session3","Quiz 1",5.0,"Alvin Chang");

    }
}
