package com.example.cindy.esc_50005.UI.Session;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cindy.esc_50005.UI.Session.feedback.FeedbackFragment;

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
                return new QuestionsFragment();
            case 1:
                return new ActivitiesFragment();
            case 2:
                return new FeedbackFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}