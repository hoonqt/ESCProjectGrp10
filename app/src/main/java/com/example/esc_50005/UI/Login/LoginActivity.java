package com.example.esc_50005.UI.Login;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;;

import android.os.Bundle;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.esc_50005.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AWSMobileClient.getInstance().initialize(this).execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Fragment fragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction();

    }
    public Fragment getFragment()
    {
        return new LoginFragment();
    }

}