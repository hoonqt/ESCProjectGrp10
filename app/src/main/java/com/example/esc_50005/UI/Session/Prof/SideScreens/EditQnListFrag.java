package com.example.esc_50005.UI.Session.Prof.SideScreens;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Prof.Adapters.QnListAdapter;
import com.example.esc_50005.UI.Session.Main.SessionActivity;
import com.example.esc_50005.UI.Session.Student.StudentActivity.MainScreen.ActivityStudentFrag;

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
    private ArrayList<QuizQuestions2DO> dataset;

    private FloatingActionButton fab;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }


    public EditQnListFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_qnlist_edit, container, false);
        quizRecycler = view.findViewById(R.id.recyclerViewQuizList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        quizRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        setFab();

        final Bundle bundle = this.getArguments();

        if (bundle != null) {
            dataset = (ArrayList<QuizQuestions2DO>)bundle.getSerializable("allthequestions");

        }

        mQnListAdapter = new QnListAdapter(dataset);

        quizRecycler.setAdapter(mQnListAdapter);

        ImageView questionAdder = view.findViewById(R.id.addbutton);

        questionAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QuizEditor editor = new QuizEditor();
                Bundle toEditor = new Bundle();
                if (dataset == null) {
                    dataset = new ArrayList<>();
                }
                toEditor.putSerializable("alltheqns",dataset);
                editor.setArguments(toEditor);

                SessionActivity myActivity = (SessionActivity) context;
                myActivity.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.profsessionhere,editor).addToBackStack(null).commit();

            }
        });

        Button backbtn = view.findViewById(R.id.back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityStudentFrag gohere = new ActivityStudentFrag();
                SessionActivity myActivity = (SessionActivity) context;
                myActivity.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.profsessionhere,gohere).addToBackStack(null).commit();
            }
        });

        return view;
    }

    public void onBackPressed() {

    }

    public void setFab() {
        fab = getActivity().findViewById(R.id.session_fab);
        fab.setVisibility(View.GONE);
    }

}
