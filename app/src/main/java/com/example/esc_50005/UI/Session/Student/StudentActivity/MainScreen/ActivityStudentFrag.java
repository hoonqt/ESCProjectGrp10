package com.example.esc_50005.UI.Session.Student.StudentActivity.MainScreen;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static com.google.common.base.Preconditions.checkNotNull;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Adapters.ActivityStudentAdapter;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Contracts.QuizStudentContract;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Presenters.ActivityStudentPresenter;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityStudentFrag extends Fragment implements QuizStudentContract.View {

    private RecyclerView quizRecycler;

    private ActivityStudentFrag.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private QuizStudentContract.Presenter mPresenter = new ActivityStudentPresenter(this);
    private ActivityStudentAdapter mQuizAdapter;
    private SharedPreferences sharedPreferences;
    private FloatingActionButton fab;

    private Context context;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public ActivityStudentFrag() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(QuizStudentContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_activity_frag, container, false);
        quizRecycler = view.findViewById(R.id.recyclerViewStudentQuiz);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        quizRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mQuizAdapter = new ActivityStudentAdapter(new ArrayList<QuizQuestions2DO>());
        quizRecycler.setAdapter(mQuizAdapter);

        context = getActivity();

        fab = getActivity().findViewById(R.id.session_fab);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        String sessionCode = sharedPreferences.getString(getString(R.string.session_id),null);

        WebSocket socket = new WebSocket();
        socket.send("sinit" + sessionCode);

        return view;
    }

    @Override
    public void showQuizes(ArrayList<QuizQuestions2DO> allthequestions) {
        mQuizAdapter.setData(allthequestions);
        mQuizAdapter.notifyDataSetChanged();
        quizRecycler.setAdapter(mQuizAdapter);

    }

    @Override
    public void showNoQuiz() {

    }

    @Override
    public void showLoadQuizError() {

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

    public void sendQuestions(String input) {

        String[] received = input.split(" ");
        if (received.length == 3) {

            String part1 = received[1].substring(0,6);
            String part2 = received[1].substring(6);
            mPresenter.loadQuizes(part1,part2,received[2]);
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

    public void setFab() {
        fab.setVisibility(View.GONE);
    }
}
