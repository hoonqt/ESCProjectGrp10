package com.example.esc_50005.UI.Session.Student.StudentActivity.SideScreens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Adapters.StudentAnswerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnsweringZoneFrag extends Fragment {

    private RecyclerView qnRecycler;

    private AnsweringZoneFrag.LayoutManagerType CurrentLayoutManagerType;
    private RecyclerView.LayoutManager LayoutManager;
    private StudentAnswerAdapter qnAdapter;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public AnsweringZoneFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.student_answer_frag, container, false);
        qnRecycler = view.findViewById(R.id.recyclerViewStudentQns);
        LayoutManager = new LinearLayoutManager(getActivity());
        CurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        qnRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Bundle bundle = this.getArguments();

        if (bundle != null) {

            ArrayList<QuizQuestions1DO> allthequestions = (ArrayList<QuizQuestions1DO>)bundle.getSerializable("allthequestions");
            qnAdapter = new StudentAnswerAdapter(allthequestions);
            qnRecycler.setAdapter(qnAdapter);

        }

        return view;
    }

}
