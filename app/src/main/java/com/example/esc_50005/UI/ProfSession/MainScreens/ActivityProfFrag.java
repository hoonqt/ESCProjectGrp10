package com.example.esc_50005.UI.ProfSession.MainScreens;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaCas;
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
import android.widget.EditText;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.ProfSession.Adapters.ActivityProfAdapter;
import com.example.esc_50005.UI.ProfSession.Contracts.QuizProfContract;
import com.example.esc_50005.UI.ProfSession.Presenters.ActivityProfPresenter;
import com.example.esc_50005.UI.ProfSession.SideScreens.ActivityInfo;
import com.example.esc_50005.UI.Session.Main.SessionActivity;
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
    private ActivityProfAdapter mQuizAdapter;
    private Context context;
    private QuizProfContract.Presenter mPresenter;

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

        context = getContext();

        mPresenter = new ActivityProfPresenter(this,context);

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

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("Enter quiz name");
                alert.setTitle("Create new activity");
                final EditText input = new EditText(getContext());
                alert.setView(input);
                alert.setNegativeButton("Submit",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String quizname = input.getText().toString();
                                ActivityInfo adder = new ActivityInfo();
                                Bundle bundle = new Bundle();
                                //bundle.putSerializable("allthequestions",mPresenter);
                                adder.setArguments(bundle);
                                SessionActivity myActivity = (SessionActivity)context;
                                myActivity.getSupportFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.slide_out_up,R.anim.slide_in_up).replace(R.id.profsessionhere,adder).addToBackStack(null).commit();
                            }
                        });
                alert.show();

            }
        });


        return view;
    }

    @Override
    public void showQuizes(ArrayList<QuizQuestions2DO> allthequestions) {

        mQuizAdapter = new ActivityProfAdapter(allthequestions);
        quizRecycler.setAdapter(mQuizAdapter);

    }

    @Override
    public void showAddedQuiz(ArrayList<QuizQuestions2DO> questionsList) {

    }

    @Override
    public void showNoQuiz() {

    }

    @Override
    public void showLoadQuizError() {


    }


}
