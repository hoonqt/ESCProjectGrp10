package com.example.esc_50005.UI.Welcome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.esc_50005.R;

;

/**
 * A login screen that offers login via email/password.
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AWSMobileClient.getInstance().initialize(this).execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        Fragment fragment = new WelcomeFragment();
        getSupportFragmentManager().beginTransaction();

    }
    public Fragment getFragment()
    {
        return new WelcomeFragment();
    }

}