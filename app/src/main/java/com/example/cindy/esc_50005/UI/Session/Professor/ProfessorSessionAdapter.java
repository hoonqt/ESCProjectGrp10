package com.example.cindy.esc_50005.UI.Session.Professor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cindy.esc_50005.UI.ProfSession.MainScreens.ActivityProfFrag;
import com.example.cindy.esc_50005.UI.Session.Student.QuestionsFragment;
import com.example.cindy.esc_50005.UI.Session.feedback.FeedbackFragment;

import com.example.cindy.esc_50005.UI.Session.Student.ActivitiesFragment;

/**
 * Created by hoonqt on 24/3/18.
 */

public class ProfessorSessionAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public ProfessorSessionAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
//                return new QuestionsFragment();
//                Log.i("question fragment","selected question fragment");
                return new ProfessionQuestionFragment();
            case 1:
                return new ActivityProfFrag();
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
