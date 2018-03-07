package com.example.cindy.esc_50005.UI.Course.FAQ;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.cindy.esc_50005.Database.SessionQuestionsRemoteDataSource;
import com.example.cindy.esc_50005.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SessionsFragment extends Fragment {


    public SessionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        AWSMobileClient.getInstance().initialize(getContext()).execute();
//        SessionQuestionsRemoteDataSource session= new SessionQuestionsRemoteDataSource();
//        session.addQuestion("Why is the sky blue?","111");
//        Log.i("addedToDb","addedToDb");
        return inflater.inflate(R.layout.sessions, container, false);
    }

}
