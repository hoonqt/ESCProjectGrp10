package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by YiLong on 30/11/17.
 */

//use ctl+i in order to implement the method required by abstract class

public class CoursePagerAdapter extends FragmentStatePagerAdapter {


    //Implement the PagerAdapter which then populates the ViewPager widget with the chosen fragment.
    //chosen Fragment means which fragment you swipe to
    int mNumOfTabs;

    public CoursePagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        userInformation = PreferenceManager.getDefaultSharedPreferences(context);
        if(userInformation.getString("UserType","").equals("student")){
            switch (position)
            {
                case 0:
                    return new SessionsFragment();
                case 1:
                    return new FaqFragment();
                case 2:
                    return new ProgressStudentFragment();
                default:
                    return null;
            }
        } else{
            switch (position)
            {
                case 0:
                    return new SessionsFragment();
                case 1:
                    return new FaqFragment();
                case 2:
                    return new ProgressStudentFragment();
                default:
                    return null;
            }
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    //TODO 2.1  - implement the method skeleton by using Alt+Enter
    //TODO 2.2  - getItem takes in a position and returns a fragment
    //            depending on the position chosen
    //TODO 2.3  - getCount returns the number of tabs

}
