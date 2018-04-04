package com.example.esc_50005.UI.Session.Student.StudentActivity.MainScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static com.google.common.base.Preconditions.checkNotNull;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Adapters.ActivityStudentAdapter;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Contracts.QuizStudentContract;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Presenters.ActivityStudentPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityStudentFrag extends Fragment implements QuizStudentContract.View {

    private RecyclerView quizRecycler;

    private ActivityStudentFrag.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private QuizStudentContract.Presenter mPresenter = new ActivityStudentPresenter(this);
    private ActivityStudentAdapter mQuizAdapter;

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

        return view;
    }

    @Override
    public void showQuizes(ArrayList<QuizQuestions2DO> allthequestions) {
        mQuizAdapter.setData(allthequestions);
        mQuizAdapter.notifyDataSetChanged();
        quizRecycler.setAdapter(mQuizAdapter);
        Log.i("set here","setter");

    }

    @Override
    public void showNoQuiz() {

    }

    @Override
    public void showLoadQuizError() {

    }
}
