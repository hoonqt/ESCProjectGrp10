package com.example.esc_50005.UI.Session.Main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.esc_50005.R;

public class SessionActivity extends AppCompatActivity {
    private Button btn;
    private SharedPreferences sharedPreferences;

    private SharedPreferences userInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AWSMobileClient.getInstance().initialize(this).execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_activity);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        setTitle(sharedPreferences.getString("SessionSelected",""));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        userInformation = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        TabLayout tabLayout=(TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.questions));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.activities));

        if(!userInformation.getString("UserType","").equals("student")) {
            tabLayout.addTab(tabLayout.newTab().setText(R.string.feedback));
        }


        //fragment is reusable cuz you can use it in other activities
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager viewPager=(ViewPager) findViewById(R.id.pager);

        final SessionPagerAdapter pagerAdapter=new SessionPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(), this.getApplicationContext());

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(sharedPreferences.getString("UserType","").equals("professor"))
        {
            int id = item.getItemId();

            if(id == R.id.end_session){
                Log.i("end","end");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("End Session", "True");
                editor.commit();
                return true;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(sharedPreferences.getString("UserType","").equals("professor"))
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return true;
        }
        return false;
    }

}