package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.cindy.esc_50005.R;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ImageButton mClickButton5 = (ImageButton)findViewById(R.id.arrow);
//        mClickButton5.setOnClickListener(this);

        TabLayout tabLayout=(TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.sessions));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.faq));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.progress));

        //fragment is reusable cuz you can use it in other activities
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager viewPager=(ViewPager) findViewById(R.id.pager);

        //The ViewPager is the widget that allows the user to swipe left or right to see an entirely new screen. In a sense, it's just a nicer way to show the user multiple tabs.
        // It also has the ability to dynamically add and remove pages (or tabs) at anytime.
//
        final com.example.cindy.esc_50005.UI.Course.FAQ.CoursePagerAdapter coursePagerAdapter=new com.example.cindy.esc_50005.UI.Course.FAQ.CoursePagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(coursePagerAdapter);

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

//    /** Called when the user touches the button */
//    public void onClick(View view) {
//        Log.i("HER","0000000000");
//
//        // Do something in response to button click
//        hey=(TextView) view.findViewById(R.id.replyTest);
//        hey.setText(R.string.app_name);
//
//    }
}
