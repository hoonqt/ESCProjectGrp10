package com.example.cindy.esc_50005.UI.ProfSession;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.cindy.esc_50005.UI.Session.Student.ActivitiesFragment;

/**
 * Created by hoonqt on 24/3/18.
 */

public class ProfSessionAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public ProfSessionAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                Log.i("question fragment","selected question fragment");
                return new ProfQuestionFrag();
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
