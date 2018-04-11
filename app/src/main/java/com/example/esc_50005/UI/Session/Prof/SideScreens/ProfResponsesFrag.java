package com.example.esc_50005.UI.Session.Prof.SideScreens;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.esc_50005.Database.QuizAnswers.QuizAnswersDO;
import com.example.esc_50005.Log;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Prof.Adapters.QnResponsesAdapter;
import com.example.esc_50005.UI.Session.Prof.Contracts.ResponseProfContract;
import com.example.esc_50005.UI.Session.Prof.Presenters.ProfResponsesPresenter;

import java.util.ArrayList;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfResponsesFrag extends Fragment implements ResponseProfContract.View {

    private RecyclerView responseRecycler;

    private ProfResponsesFrag.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private QnResponsesAdapter ResponseAdapter;
    private Context context;
    private ResponseProfContract.Presenter mPresenter;

    private String courseCode;
    private String sessionID;

    SharedPreferences sharedPreferences;

    TextView questionBox;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }


    public ProfResponsesFrag() {
        // Required empty public constructor
    }

    @Override
    public void setPresenter(ResponseProfContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        context = getActivity();

        mPresenter = new ProfResponsesPresenter(this);

        View view = inflater.inflate(R.layout.prof_question_frag, container, false);
        responseRecycler = view.findViewById(R.id.recyclerViewProfResponses);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        responseRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);


        courseCode = sharedPreferences.getString(context.getResources().getString(R.string.course_id), null);
        sessionID = sharedPreferences.getString(context.getResources().getString(R.string.session_id),null);

        Bundle bundle = this.getArguments();

        String quizName = "";
        String question = "";

        if (bundle != null) {
            quizName = bundle.getString("quizName");
            question = bundle.getString("questionName");
        }


        mPresenter.loadResponses(courseCode,sessionID,quizName);

        questionBox = view.findViewById(R.id.questionBox);

        questionBox.setText(question);




        return view;
    }

    @Override
    public void showResponses(ArrayList<QuizAnswersDO> allthequestions) {

        ResponseAdapter = new QnResponsesAdapter(allthequestions);
        responseRecycler.setAdapter(ResponseAdapter);

    }

    @Override
    public void showNoResponses() {

    }
}
