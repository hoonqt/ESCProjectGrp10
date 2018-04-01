package com.example.esc_50005.WebSocket;

import android.app.Application;

import com.example.esc_50005.UI.Session.Student.StudentActivity.Presenters.ActivityStudentPresenter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by hoonqt on 27/3/18.
 */

import android.app.Application;

import com.example.esc_50005.UI.Session.Student.StudentActivity.Presenters.ActivityStudentPresenter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by hoonqt on 27/3/18.
 */

public class ProfWebSocket {

    OkHttpClient client;
    private static okhttp3.WebSocket ws;
    private static ProfWebSocket instance;
    private EchoWebSocketListener listener;

    private ProfWebSocket() {

        client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://ec2-54-175-239-77.compute-1.amazonaws.com:3000").build();
        this.listener = new EchoWebSocketListener();
        ws = client.newWebSocket(request, listener);

    }

    public static synchronized ProfWebSocket getInstance() {

        if (instance == null) {
            instance = new ProfWebSocket();

        }

        return instance;

    }

    public void sendMsg(String input) {

        ws.send(input);

    }


    private void processMsg(String input) {


    }


    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(okhttp3.WebSocket webSocket, Response response) {

        }
        @Override
        public void onMessage(okhttp3.WebSocket webSocket, String text) {
            processMsg(text);
        }
        @Override
        public void onMessage(okhttp3.WebSocket webSocket, ByteString bytes) {
        }
        @Override
        public void onClosing(okhttp3.WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }
        @Override
        public void onFailure(okhttp3.WebSocket webSocket, Throwable t, Response response) {
        }
    }

    public void start() {
        ws.send("pinita113");
    }

    public void end() {
        client.dispatcher().executorService().shutdown();
    }

}