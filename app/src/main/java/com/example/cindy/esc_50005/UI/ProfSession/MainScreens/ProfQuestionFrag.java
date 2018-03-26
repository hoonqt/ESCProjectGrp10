package com.example.cindy.esc_50005.UI.ProfSession.MainScreens;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.ProfSession.Adapters.ProfQnAdapter;
import com.example.cindy.esc_50005.UI.ProfSession.Contracts.QnProfContract;
import com.example.cindy.esc_50005.UI.ProfSession.Presenters.ProfQnPresenter;


import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfQuestionFrag extends Fragment implements QnProfContract.View {

    private RecyclerView questionsrecycler;

    private ProfQuestionFrag.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private QnProfContract.Presenter mPresenter = new ProfQnPresenter(this);
    private ProfQnAdapter mQnAdapter;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public ProfQuestionFrag() {
        // Required empty public constructor
    }

    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull QnProfContract.Presenter presenter) {
        Log.i("set presenter","set presenter");
        mPresenter = checkNotNull(presenter);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_prof_question, container, false);
        questionsrecycler=view.findViewById(R.id.recyclerViewProfQn);
        mLayoutManager= new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = ProfQuestionFrag.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        questionsrecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mQnAdapter = new ProfQnAdapter();
        questionsrecycler.setAdapter(mQnAdapter);

        return view;
    }

    @Override
    public void showAddedQuestion(ArrayList<SessionQuestionsDO> questionsList) {

    }

    @Override
    public void showLoadQuestionsError() {

    }

    @Override
    public void showQuestions() {



    }

    @Override
    public void showNoQuestions() {

    }
}
