package com.example.esc_50005.UI.Dashboard.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.esc_50005.MainActivity;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Course.FAQ.session.professor.ProfessorSessionsFragment;
import com.example.esc_50005.UI.Course.FAQ.session.student.StudentSessionsFragment;
import com.example.esc_50005.UI.Dashboard.professor.ProfessorDashboardFragment;
import com.example.esc_50005.UI.Dashboard.professor.ProfessorDashboardFragment;

import com.example.esc_50005.UI.Login.LoginFragment;
import com.example.esc_50005.UI.Session.Main.SessionActivity;

;


public class DashboardActivity extends MainActivity{

    private Button button;
    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        AWSMobileClient.getInstance().initialize(this).execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        switch(sharedPreferences.getString(getString(R.string.user_type),""))
        {
            case("professor"):
                Log.i("load prof","load prof");
                Fragment professorFragment = new ProfessorDashboardFragment();
                getSupportFragmentManager().beginTransaction();
                break;
            case("student"):
                Log.i("load student","load student");
                Fragment professorFragment2 = new StudentSessionsFragment();
                getSupportFragmentManager().beginTransaction();
                break;

        }

    }
    public Fragment getFragment()
    {
        return new LoginFragment();
    }

}
