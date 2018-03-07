package com.example.cindy.esc_50005.UI.Session;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by 1002215 on 20/2/18.
 */

public class SessionPagerAdapter extends FragmentStatePagerAdapter {


    //Implement the PagerAdapter which then populates the ViewPager widget with the chosen fragment.
    //chosen Fragment means which fragment you swipe to
    int mNumOfTabs;

    public SessionPagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new QuestionsFragment();
            case 1:
                return new ActivitiesFragment();
            default:
                return null;
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