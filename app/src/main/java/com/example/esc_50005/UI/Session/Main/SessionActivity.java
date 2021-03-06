package com.example.esc_50005.UI.Session.Main;

import android.app.AlertDialog;
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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Prof.MainScreens.ActivityProfFrag;
import com.example.esc_50005.UI.Session.Student.QuestionsFragment;
import com.example.esc_50005.UI.Session.Student.StudentActivity.MainScreen.ActivityStudentFrag;
import com.example.esc_50005.UI.Session.feedback.FeedbackProfFragment;
import com.example.esc_50005.UI.Session.feedback.FeedbackStudentFragment;

public class SessionActivity extends AppCompatActivity {
    private Button btn;
    private SharedPreferences sharedPreferences;

    private SharedPreferences userInformation;
    private TextView tv_toolbar_title;
    private TextView tv_toolbar_code;
    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AWSMobileClient.getInstance().initialize(this).execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_activity);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        setTitle(sharedPreferences.getString("SessionSelected", ""));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        userInformation = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userType = sharedPreferences.getString(getString(R.string.user_type), "");

        tv_toolbar_title = (TextView) findViewById(R.id.toolbar_course_name);
        tv_toolbar_title.setText(sharedPreferences.getString(getString(R.string.session_name), ""));
        tv_toolbar_code = (TextView) findViewById(R.id.toolbar_course_code);
        String code = "Session Code: " + sharedPreferences.getString(getString(R.string.session_id), "");
        tv_toolbar_code.setText(code);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.questions));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.activities));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.feedback));

        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        final SessionPagerAdapter pagerAdapter = new SessionPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), this.getApplicationContext());

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 2) {
                    if (userType.equals("student")) {
                        FeedbackStudentFragment frag = (FeedbackStudentFragment) viewPager
                                .getAdapter()
                                .instantiateItem(viewPager, viewPager.getCurrentItem());
                        frag.setFab();
                    } else {
                        FeedbackProfFragment frag = (FeedbackProfFragment) viewPager
                                .getAdapter()
                                .instantiateItem(viewPager, viewPager.getCurrentItem());
                        frag.setFab();
                    }
                } else if (tab.getPosition() == 1) {
                    if (userType.equals("student")) {
                        ActivityStudentFrag frag = (ActivityStudentFrag) viewPager
                                .getAdapter()
                                .instantiateItem(viewPager, viewPager.getCurrentItem());
                        frag.setFab();
                    } else {
                        ActivityProfFrag frag = (ActivityProfFrag) viewPager
                                .getAdapter()
                                .instantiateItem(viewPager, viewPager.getCurrentItem());
                        frag.setFab();
                    }
                } else if (tab.getPosition() == 0) {
                    QuestionsFragment frag = (QuestionsFragment) viewPager
                            .getAdapter()
                            .instantiateItem(viewPager, viewPager.getCurrentItem());
                    frag.setFab();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (sharedPreferences.getString(getString(R.string.user_type), "").equals("professor")) {
            int id = item.getItemId();

            if (id == R.id.get_session_id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SessionActivity.this);
                builder.setTitle("The sesson id is " + sharedPreferences.getString(getString(R.string.session_id), ""));
                builder.create();
                builder.show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (sharedPreferences.getString(getString(R.string.user_type), "").equals("professor")) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return true;
        }
        return false;
    }

}