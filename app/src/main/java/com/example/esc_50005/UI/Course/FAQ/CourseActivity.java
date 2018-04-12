package com.example.esc_50005.UI.Course.FAQ;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Course.FAQ.editFaq.EditFaqDialog;
import com.example.esc_50005.UI.Course.FAQ.session.professor.ProfessorSessionsFragment;
import com.example.esc_50005.UI.Course.FAQ.session.student.StudentSessionsFragment;
import com.example.esc_50005.UI.Session.Main.SessionActivity;

import okhttp3.OkHttpClient;

public class CourseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private OkHttpClient client;
    private SharedPreferences sharedPreferences;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private TextView tv_toolbar_title;
    private TextView tv_toolbar_code;
    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);


        AWSMobileClient.getInstance().initialize(this).execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        userType = sharedPreferences.getString(getString(R.string.user_type),"");
        setTitle(sharedPreferences.getString(getString(R.string.course_name),""));

        tv_toolbar_title = (TextView) findViewById(R.id.toolbar_course_name);
        tv_toolbar_title.setText(sharedPreferences.getString(getString(R.string.course_name),""));
        tv_toolbar_code = (TextView) findViewById(R.id.toolbar_course_code);
        String code = "Course Code: " + sharedPreferences.getString(getString(R.string.course_id),"");
        tv_toolbar_code.setText(code);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.sessions));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.faq));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.progress));

        //fragment is reusable cuz you can use it in other activities
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final com.example.esc_50005.UI.Course.FAQ.CoursePagerAdapter coursePagerAdapter = new com.example.esc_50005.UI.Course.FAQ.CoursePagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),this.getApplicationContext());

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.course_fab);

        viewPager.setAdapter(coursePagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 2) {
                    if (userType.equals("student")) {
                        NameListFragment frag = (NameListFragment) viewPager
                                .getAdapter()
                                .instantiateItem(viewPager, viewPager.getCurrentItem());
                        frag.setFab();
                    } else {
                        ProgressStudentFragment frag = (ProgressStudentFragment) viewPager
                                .getAdapter()
                                .instantiateItem(viewPager,viewPager.getCurrentItem());
                        frag.setFab();
                    }
                } else if (tab.getPosition() == 1) {
                    FaqFragment frag1 = (FaqFragment) viewPager
                            .getAdapter()
                            .instantiateItem(viewPager, viewPager.getCurrentItem());
                    frag1.setFab();
                } else if (tab.getPosition() == 0){
                    if (userType.equals("student")) {
                        StudentSessionsFragment frag = (StudentSessionsFragment) viewPager
                                .getAdapter()
                                .instantiateItem(viewPager, viewPager.getCurrentItem());
                        frag.setFab();
                    } else {
                        ProfessorSessionsFragment frag = (ProfessorSessionsFragment)viewPager
                                .getAdapter()
                                .instantiateItem(viewPager, viewPager.getCurrentItem());
                        frag.setFab();
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        GetQuestionTask task = new GetQuestionTask();
//        task.execute();
    }

    @Override
    public void onClick(View v) {
        Log.i("this","button is pressed");
        Intent intent = new Intent(CourseActivity.this, SessionActivity.class);
        startActivity(intent);
        finish();

    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_frame, fragment, "TEST")
                .commit();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
    }

    public void showBottomSheetDialog(View view) {
    }
}
