package com.example.cindy.esc_50005.UI.ProfSession;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.cindy.esc_50005.R;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfQuizFrag extends Fragment implements ProfQuizContract.View {

    private RecyclerView quizRecycler;

    private ProfQuizFrag.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProfQuizContract.Presenter mPresenter = new ProfQuizPresenter(this);
    private ProfQuizAdapter mQuizAdapter;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public ProfQuizFrag() {
        // Required empty public constructor
    }

    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull ProfQuizContract.Presenter presenter) {
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

        mQuizAdapter = new ProfQuizAdapter();
        quizRecycler.setAdapter(mQuizAdapter);

        return view;
    }

    @Override
    public void showQuizes() {

    }

    @Override
    public void showAddedQuiz(ArrayList<SessionQuestionsDO> questionsList) {

    }

    @Override
    public void showNoQuiz() {

    }

    @Override
    public void showLoadQuizError() {

    }
}
