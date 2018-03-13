package com.example.cindy.esc_50005.UI.Course.FAQ;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.cindy.esc_50005.Database.activityQuestion.questionCreator;
import com.example.cindy.esc_50005.R;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class SessionsFragment extends Fragment {

    //QT stuff
    private OkHttpClient client;
    TextView thetext;


    public SessionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sessions, container, false);


        // Inflate the layout for this fragment
//        AWSMobileClient.getInstance().initialize(getContext()).execute();
//        SessionQuestionsRemoteDataSource session= new SessionQuestionsRemoteDataSource();
//        session.addQuestion("Why is the sky blue?","111");
//        Log.i("addedToDb","addedToDb");
        return view;
    }


}
