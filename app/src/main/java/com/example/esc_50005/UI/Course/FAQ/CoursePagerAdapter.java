package com.example.esc_50005.UI.Course.FAQ;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.esc_50005.R;
import com.example.esc_50005.UI.Course.FAQ.session.professor.ProfessorSessionsFragment;
import com.example.esc_50005.UI.Course.FAQ.session.student.StudentSessionsFragment;

/**
 * Created by YiLong on 30/11/17.
 */

//use ctl+i in order to implement the method required by abstract class

public class CoursePagerAdapter extends FragmentStatePagerAdapter {


    //Implement the PagerAdapter which then populates the ViewPager widget with the chosen fragment.
    //chosen Fragment means which fragment you swipe to
    int mNumOfTabs;
    private Context context;
    private SharedPreferences userInformation;

    public CoursePagerAdapter(FragmentManager fm, int mNumOfTabs, Context context) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        userInformation = PreferenceManager.getDefaultSharedPreferences(context);
        if(userInformation.getString("UserType","").equals("student")) {
            switch (position) {
                case 0:
                    return new StudentSessionsFragment();
                case 1:
                    return new FaqFragment();
                case 2:
                    return new ProgressStudentFragment();
                default:
                    return null;
            }
        }
        else{

            switch (position)
            {
                case 0:
                    return new ProfessorSessionsFragment();
                case 1:
                    return new FaqFragment();
                case 2:
                    return new NameListFragment();
                default:
                    return null;
            }
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}
