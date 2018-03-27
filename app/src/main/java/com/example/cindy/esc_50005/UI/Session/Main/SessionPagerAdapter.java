package com.example.cindy.esc_50005.UI.Session.Main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.cindy.esc_50005.UI.ProfSession.MainScreens.ActivityProfFrag;
import com.example.cindy.esc_50005.UI.ProfSession.MainScreens.ProfQuestionFrag;
import com.example.cindy.esc_50005.UI.Session.Student.QuestionsFragment;

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
                    return new QuestionsFragment();
                default:
                    return null;
            }
        }

        else{
            Log.i("prof fragment","prof fragment");
            switch (position)
            {
                case 0:
                    return new ProfQuestionFrag();
                case 1:
                    return new ActivityProfFrag();
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