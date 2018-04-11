package com.example.esc_50005.UI.Session.Prof.MainScreens;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Prof.Adapters.ActivityProfAdapter;
import com.example.esc_50005.UI.Session.Prof.Contracts.QuizProfContract;
import com.example.esc_50005.UI.Session.Prof.Presenters.ActivityProfPresenter;
import com.example.esc_50005.UI.Session.Main.SessionActivity;
import com.example.esc_50005.UI.Session.Prof.SideScreens.EditQnListFrag;
import com.example.esc_50005.UI.Session.Prof.SideScreens.QuizEditor;
import com.example.esc_50005.WebSocket.ProfWebSocket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

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
    SharedPreferences sharedPreferences;

    String courseCode;
    String sessionID;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public ActivityProfFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        context = getActivity();

        mPresenter = new ActivityProfPresenter(this,context);

        View view = inflater.inflate(R.layout.fragment_prof_quiz, container, false);
        quizRecycler = view.findViewById(R.id.recyclerViewProfQuiz);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        quizRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        courseCode = sharedPreferences.getString(context.getResources().getString(R.string.course_id), null);
        sessionID = sharedPreferences.getString(context.getResources().getString(R.string.session_id),null);

        mPresenter.loadQuizes(courseCode,sessionID);

        FloatingActionButton fab = view.findViewById(R.id.fabbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final SharedPreferences.Editor edithere = sharedPreferences.edit();

                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.prof_qn_popup);

                final EditText input = dialog.findViewById(R.id.quizName);
                final RadioGroup rg = dialog.findViewById(R.id.radiobtns);

                TextView submitbtn = dialog.findViewById(R.id.submit);
                submitbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int selected = rg.getCheckedRadioButtonId();

                        if (selected == R.id.quizBubble) {
                            edithere.putString("ActivityType","Quiz");
                        }

                        else {
                            edithere.putString("ActivityType","Question");
                        }


                        String quizname = input.getText().toString();


                        edithere.putString("QuizName",quizname);
                        edithere.commit();

                        String ActivityType = sharedPreferences.getString("ActivityType",null);

                        Fragment editQnfrag;

                        if (ActivityType.equals("Quiz")) {
                            editQnfrag = new EditQnListFrag();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("allthequestions",new ArrayList<QuizQuestions2DO>());
                            editQnfrag.setArguments(bundle);
                            SessionActivity myActivity = (SessionActivity)context;
                            myActivity.getSupportFragmentManager().beginTransaction().replace(R.id.profsessionhere,editQnfrag).addToBackStack(null).commit();
                        }

                        else {
                            QnCreator(input.getText().toString());
                        }


                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });


        return view;
    }

    @Override
    public void showQuizes(ArrayList<QuizQuestions2DO> allthequestions) {

        mQuizAdapter = new ActivityProfAdapter(allthequestions,context);
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

    @Override
    public Context getContext() {
        return context;
    }

    void QnCreator(final String qnName) {

        AlertDialog.Builder qnBuilder = new AlertDialog.Builder(getActivity());
        qnBuilder.setTitle("Create " + qnName);
        qnBuilder.setMessage("Enter question");
        final EditText input = new EditText(getContext());
        qnBuilder.setView(input);

        qnBuilder.setNegativeButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                QuizQuestions2DO tobeadded = new QuizQuestions2DO();
                tobeadded.setQuizNameQnID(qnName + " " + getSaltString());
                tobeadded.setSubjectCodeSessionCode(sharedPreferences.getString(getString(R.string.course_id), null)+sharedPreferences.getString(getString(R.string.session_id),""));
                tobeadded.setIsItQn(true);
                tobeadded.setQuestion(input.getText().toString());

                QuizRemoteDataSource adder = new QuizRemoteDataSource();
                adder.putQuestion(tobeadded);

                dialogInterface.dismiss();
            }
        });

        qnBuilder.show();


    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}