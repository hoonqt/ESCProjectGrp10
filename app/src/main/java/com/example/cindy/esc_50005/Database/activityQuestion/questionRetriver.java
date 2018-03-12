package com.example.cindy.esc_50005.Database.activityQuestion;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by hoonqt on 5/3/18.
 */

public class questionRetriver {

    JSONObject allQnsfromSession;
    private OkHttpClient client;

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(WebSocket webSocket, Response response) {

        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            questionRetriver(text.substring(0,1),text.substring(2,3));
        }

    }



    public void questionRetriver(String courseID, String sessionId) {

        questionCreator gatherer = new questionCreator();

    }

    public void convertJSON() {

        try {
            String don = allQnsfromSession.getString("_question");
        }

        catch (JSONException ex) {

        }


    }

}
