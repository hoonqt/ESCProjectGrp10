package com.example.cindy.esc_50005.WebSocket;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by hoonqt on 27/3/18.
 */

public class WebSocket {

    OkHttpClient client;

    public WebSocket() {

        client = new OkHttpClient();

    }

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(okhttp3.WebSocket webSocket, Response response) {

        }
        @Override
        public void onMessage(okhttp3.WebSocket webSocket, String text) {

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
        Request request = new Request.Builder().url("ws://ec2-54-175-239-77.compute-1.amazonaws.com:3000").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        okhttp3.WebSocket ws = client.newWebSocket(request, listener);
        ws.send("pinita113");
        ws.send("psenda113");

    }

    public void end() {
        client.dispatcher().executorService().shutdown();
    }

}
