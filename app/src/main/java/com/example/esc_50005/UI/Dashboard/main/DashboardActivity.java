package com.example.esc_50005.UI.Dashboard.main;

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
import com.example.esc_50005.UI.Dashboard.professor.ProfessorDashboardFragment;
import com.example.esc_50005.UI.Dashboard.professor.ProfessorDashboardFragment;
import com.example.esc_50005.UI.Dashboard.student.StudentDashboardFragment;
import com.example.esc_50005.UI.Login.LoginFragment;
import com.example.esc_50005.UI.Session.Main.SessionActivity;

;

/**
 * Created by cindy on 22/3/2018.
 */

public class DashboardActivity extends MainActivity{

    private Button button;
    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        AWSMobileClient.getInstance().initialize(this).execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        switch(sharedPreferences.getString("UserType",""))
        {
            case("professor"):
                Log.i("load prof","load prof");
                Fragment professorFragment = new ProfessorDashboardFragment();
                getSupportFragmentManager().beginTransaction();
                break;
            case("student"):
                Log.i("load student","load student");
                Fragment studentFragment = new StudentDashboardFragment();
                getSupportFragmentManager().beginTransaction();
                break;

        }

    }
    public Fragment getFragment()
    {
        return new LoginFragment();
    }

}
