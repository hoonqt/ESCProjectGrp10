package com.example.cindy.esc_50005.UI.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.CourseActivity;
import com.example.cindy.esc_50005.UI.Login.LoginFragment;
import com.example.cindy.esc_50005.UI.Session.Main.SessionActivity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;;

/**
 * Created by cindy on 22/3/2018.
 */

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AWSMobileClient.getInstance().initialize(this).execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        button = findViewById(R.id.clickToGoSessionActivity);
        button.setOnClickListener(this);
        Fragment fragment = new DashboardFragment();
        getSupportFragmentManager().beginTransaction();

    }
    public Fragment getFragment()
    {
        return new LoginFragment();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DashboardActivity.this, SessionActivity.class);
        startActivity(intent);
        finish();

    }
}
