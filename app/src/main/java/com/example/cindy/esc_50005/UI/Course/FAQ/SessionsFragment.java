package com.example.cindy.esc_50005.UI.Course.FAQ;


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
    JSONObject allQnsfromSession;
    TextView thetext;


    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(WebSocket webSocket, Response response) {

            webSocket.send("donald");

        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            questionRetriver(text.substring(0,2),text.substring(2));
        }

    }

    public void questionRetriver(String courseID, String sessionId) {

        questionCreator gatherer = new questionCreator();
        allQnsfromSession = gatherer.getDatainjson("s1","q1");

        String output = convertJSON();

        thetext.setText(sessionId);
    }

    public String convertJSON() {

        String don = "";

        try {
            don = allQnsfromSession.getString("_question");
        }

        catch (JSONException ex) {

        }

        return don;


    }

    private void start() {
        client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://10.0.2.2:8083").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }

    //QT stuff


    public SessionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sessions, container, false);

        thetext = view.findViewById(R.id.newquizbox);
        start();





        // Inflate the layout for this fragment
//        AWSMobileClient.getInstance().initialize(getContext()).execute();
//        SessionQuestionsRemoteDataSource session= new SessionQuestionsRemoteDataSource();
//        session.addQuestion("Why is the sky blue?","111");
//        Log.i("addedToDb","addedToDb");
        return view;
    }

}
