package com.example.esc_50005.UI.Session.Main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.esc_50005.UI.Session.Prof.MainScreens.ActivityProfFrag;
import com.example.esc_50005.UI.Session.Student.QuestionsFragment;
import com.example.esc_50005.UI.Session.Student.StudentActivity.MainScreen.ActivityStudentFrag;
import com.example.esc_50005.UI.Session.feedback.FeedbackFragment;

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

        if(userInformation.getString("UserType","").equals("student"))
        {
            switch (position)
            {
                case 0:
                    return new QuestionsFragment();
                case 1:
                    return new ActivityStudentFrag();
                default:
                    return null;
            }
        }

        else{
            switch (position)
            {
                case 0:
                    return new QuestionsFragment();
                case 1:
                    return new ActivityProfFrag();
                case 2:
                    return new FeedbackFragment();
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