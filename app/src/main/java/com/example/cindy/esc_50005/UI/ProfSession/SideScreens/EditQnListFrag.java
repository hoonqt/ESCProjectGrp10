package com.example.cindy.esc_50005.UI.ProfSession.SideScreens;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cindy.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.ProfSession.Adapters.QnListAdapter;
import com.example.cindy.esc_50005.UI.ProfSession.MainScreens.ActivityProfFrag;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditQnListFrag extends Fragment implements Serializable {
    private Context context;

    private RecyclerView quizRecycler;
    private QnListAdapter mQnListAdapter;
    private EditQnListFrag.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<QuizQuestions1DO> dataset;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }


    public EditQnListFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_qnlist_edit, container, false);
        quizRecycler = view.findViewById(R.id.recyclerViewQuizList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        quizRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            dataset = (ArrayList<QuizQuestions1DO>)bundle.getSerializable("allthequestions");
        }

        mQnListAdapter = new QnListAdapter(dataset);

        quizRecycler.setAdapter(mQnListAdapter);

        return view;
    }

}
