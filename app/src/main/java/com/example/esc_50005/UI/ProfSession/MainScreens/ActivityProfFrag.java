package com.example.esc_50005.UI.ProfSession.MainScreens;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.ProfSession.Adapters.ActivityProfAdapter;
import com.example.esc_50005.UI.ProfSession.Contracts.QuizProfContract;
import com.example.esc_50005.UI.ProfSession.Presenters.ActivityProfPresenter;
import com.example.esc_50005.WebSocket.ProfWebSocket;

import java.io.Serializable;
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityProfFrag extends Fragment implements QuizProfContract.View, Serializable {

    private RecyclerView quizRecycler;

    private ActivityProfFrag.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private QuizProfContract.Presenter mPresenter = new ActivityProfPresenter(this);
    private ActivityProfAdapter mQuizAdapter;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public ActivityProfFrag() {
        // Required empty public constructor
    }

    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull QuizProfContract.Presenter presenter) {
        Log.i("set presenter","set presenter");
        mPresenter = checkNotNull(presenter);
    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_prof_quiz, container, false);
        quizRecycler = view.findViewById(R.id.recyclerViewProfQuiz);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        quizRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ProfWebSocket socket = ProfWebSocket.getInstance();
        socket.start();

        FloatingActionButton fab = view.findViewById(R.id.fabbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }

    @Override
    public void showQuizes(ArrayList<QuizQuestions1DO> allthequestions) {

        mQuizAdapter = new ActivityProfAdapter(allthequestions);
        quizRecycler.setAdapter(mQuizAdapter);

    }

    @Override
    public void showAddedQuiz(ArrayList<QuizQuestions1DO> questionsList) {

    }

    @Override
    public void showNoQuiz() {

    }

    @Override
    public void showLoadQuizError() {

    }


}
