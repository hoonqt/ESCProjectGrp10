package com.example.esc_50005.UI.Session.Main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Prof.MainScreens.ActivityProfFrag;
import com.example.esc_50005.UI.Session.Student.QuestionsFragment;
import com.example.esc_50005.UI.Session.Student.StudentActivity.MainScreen.ActivityStudentFrag;
import com.example.esc_50005.UI.Session.feedback.FeedbackProfFragment;
import com.example.esc_50005.UI.Session.feedback.FeedbackStudentFragment;

public class SessionPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    private SharedPreferences userInformation;
    private Context context;

    public SessionPagerAdapter(FragmentManager fm, int mNumOfTabs, Context context) {
        super(fm);
        this.context=context;
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        userInformation = PreferenceManager.getDefaultSharedPreferences(context);

        if(userInformation.getString((context.getResources().getString(R.string.user_type)),"").equals("student"))
        {
            switch (position)
            {
                case 0:
                    return new QuestionsFragment();
                case 1:
                    return new ActivityStudentFrag();
                case 2:
                    return new FeedbackStudentFragment();
                default:
                    return null;
            }
        }

        else if (userInformation.getString((context.getResources().getString(R.string.user_type)),"").equals("professor")) {
            switch (position)
            {
                case 0:
                    return new QuestionsFragment();
                case 1:
                    return new ActivityProfFrag();
                case 2:
                    return new FeedbackProfFragment();
                default:
                    return null;
            }

        }

        return null;

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}