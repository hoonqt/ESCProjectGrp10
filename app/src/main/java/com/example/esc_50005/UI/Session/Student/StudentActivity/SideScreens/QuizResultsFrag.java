package com.example.esc_50005.UI.Session.Student.StudentActivity.SideScreens;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Main.SessionActivity;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Adapters.ResultsAdapter;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Adapters.StudentAnswerAdapter;
import com.example.esc_50005.UI.Session.Student.StudentActivity.MainScreen.ActivityStudentFrag;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizResultsFrag extends Fragment {

    private RecyclerView resultRecycler;

    private QuizResultsFrag.LayoutManagerType CurrentLayoutManagerType;
    private RecyclerView.LayoutManager LayoutManager;
    private ResultsAdapter ansAdapter;
    private ArrayList<QuizQuestions2DO> questionList;
    private ArrayList<Integer> options;
    private Integer score;

    Button closeBtn;
    TextView scoreView;
    SharedPreferences sharedPreferences;
    Context context;


    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public QuizResultsFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.studentquiz_results, container, false);
        resultRecycler = view.findViewById(R.id.recyclerViewStudentAns);
        LayoutManager = new LinearLayoutManager(getActivity());
        CurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        resultRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        context = getContext();

        closeBtn = view.findViewById(R.id.closebutton);
        closeBtn.setOnClickListener(closeClick);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            questionList = (ArrayList<QuizQuestions2DO>)bundle.getSerializable("QnList");
            options = (ArrayList<Integer>)bundle.getSerializable("StudentInput");
            score = bundle.getInt("ScoreInt");
            Log.i("Beacon",questionList.toString());
            ansAdapter =  new ResultsAdapter(questionList,options);
            resultRecycler.setAdapter(ansAdapter);
        }

        scoreView = view.findViewById(R.id.scoreHere);

        double inpercent = Math.round(((double)score/(double)questionList.size() )*100);

        scoreView.setText("Your score is:" + score + "/" + questionList.size() + "\nPercentage is: " + inpercent );


        return view;
    }

    private View.OnClickListener closeClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            ActivityStudentFrag tobestarted = new ActivityStudentFrag();
            SessionActivity myActivity = (SessionActivity) context;
            FragmentManager fm = getActivity().getSupportFragmentManager();
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }

        }
    };

}
