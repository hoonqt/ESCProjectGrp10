package com.example.cindy.esc_50005.UI.Dashboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Login.LoginFragment;
import android.support.v7.app.AppCompatActivity;;

/**
 * Created by cindy on 22/3/2018.
 */

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AWSMobileClient.getInstance().initialize(this).execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        Fragment fragment = new DashboardFragment();
        getSupportFragmentManager().beginTransaction();

    }
    public Fragment getFragment()
    {
        return new LoginFragment();
    }
}
