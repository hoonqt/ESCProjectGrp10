package com.example.esc_50005.UI.Session.Student.StudentActivity.Presenters;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Contracts.QuizStudentContract;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okio.ByteString;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hoonqt on 30/3/18.
 */

public class ActivityStudentPresenter implements QuizStudentContract.Presenter {

    private final QuizStudentContract.View mQuizStudentView;
    private QuizRemoteDataSource mQuizQuestionsRepository;
    private ArrayList<QuizQuestions2DO> questionData;

    private String courseCode;
    private String sessionID;

    public ActivityStudentPresenter(QuizStudentContract.View fragment) {

        mQuizQuestionsRepository = new QuizRemoteDataSource();
        mQuizStudentView = checkNotNull(fragment,"Quiz not null");
        mQuizStudentView.setPresenter(this);


    }

    @Override
    public void start() {

        WebSocket socket = new WebSocket();
        socket.send("sinita113");
        loadQuizes("50.005","Process and Thread");


    }

    public void sendQuestions(String input) {

        String part1 = input.substring(0,6);
        String part2 = input.substring(6);
        loadQuizes(part1,part2);

    }



    @Override
    public void loadQuizes(String subjectCode, String sessionCode) {
        questionData = mQuizQuestionsRepository.getQuestions(subjectCode,sessionCode);
        mQuizStudentView.showQuizes(questionData);
        Log.i("Size of data:", Integer.toString(questionData.size()));
    }

    @Override
    public void processEmptyQuiz() {

    }

    @Override
    public ArrayList<QuizQuestions2DO> getQuestionData(String subjectCode, String sessionCode) {
        return null;
    }

    private class WebSocket {

        OkHttpClient client;
        private okhttp3.WebSocket ws;
        private WebSocket instance;
        private EchoWebSocketListener listener;

        private WebSocket() {

            client = new OkHttpClient();
            Request request = new Request.Builder().url("ws://ec2-54-175-239-77.compute-1.amazonaws.com:3000").build();
            listener = new EchoWebSocketListener();
            ws = client.newWebSocket(request, listener);

        }

        public void send(String message) {
            ws.send(message);
        }

    }

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(okhttp3.WebSocket webSocket, Response response) {

        }
        @Override
        public void onMessage(okhttp3.WebSocket webSocket, String text) {
            sendQuestions(text);
            Log.i("received",text);
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

}
