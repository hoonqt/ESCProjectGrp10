package com.example.esc_50005.UI.ProfSession;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.esc_50005.UI.ProfSession.MainScreens.ActivityProfFrag;
import com.example.esc_50005.UI.ProfSession.MainScreens.FeedbackFragProf;
import com.example.esc_50005.UI.ProfSession.MainScreens.ProfQuestionFrag;

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
                return new ProfQuestionFrag();
            case 1:
                return new ActivityProfFrag();
            default:
                return new FeedbackFragProf();
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
