package com.example.cindy.esc_50005.UI.Session;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class SessionPagerAdapter extends FragmentStatePagerAdapter {

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
                Log.i("question fragment","selected question fragment");
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


}