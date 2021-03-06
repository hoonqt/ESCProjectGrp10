package com.example.esc_50005.UI.Session.Student.StudentActivity.SideScreens;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.esc_50005.Database.Progress.ProgressRemoteDataSource;
import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Main.SessionActivity;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Adapters.StudentAnswerAdapter;
import com.example.esc_50005.UI.Session.Student.StudentActivity.MainScreen.ActivityStudentFrag;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnsweringZoneFrag extends Fragment {

    private RecyclerView qnRecycler;

    private AnsweringZoneFrag.LayoutManagerType CurrentLayoutManagerType;
    private RecyclerView.LayoutManager LayoutManager;
    private StudentAnswerAdapter qnAdapter;
    ArrayList<QuizQuestions2DO> allthequestions;
    Button submitbtn;
    SharedPreferences sharedPreferences;
    Context context;

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
        qnRecycler.setLayoutManager(LayoutManager);

        context = getContext();

        submitbtn = view.findViewById(R.id.submitbutton);
        submitbtn.setOnClickListener(submitclick);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        Bundle bundle = this.getArguments();

        if (bundle != null) {

            allthequestions = (ArrayList<QuizQuestions2DO>)bundle.getSerializable("allthequestions");
            qnAdapter = new StudentAnswerAdapter(allthequestions);
            qnRecycler.setAdapter(qnAdapter);

        }

        return view;
    }

    private View.OnClickListener submitclick = new View.OnClickListener() {

        RadioGroup group;
        int score = 0;

        @Override
        public void onClick(View view) {

            ArrayList<Integer> input = qnAdapter.getSelectedOptions();
            for (int i = 0;i<input.size();i++) {

                if ((double)input.get(i) == allthequestions.get(i).getCorrectans()) {
                    score++;
                }

            }

            ProgressRemoteDataSource data = new ProgressRemoteDataSource();

            String userID = sharedPreferences.getString(getString(R.string.user_id), null);
            String quizName = sharedPreferences.getString(getString(R.string.quiz_name),null);
            String courseCode = sharedPreferences.getString(getString(R.string.course_id),null);
            String userName = sharedPreferences.getString(getString(R.string.full_name),null);

            double inpercent = Math.round(((double)score/(double)input.size())*100);

            data.putScores(userID,courseCode,"111",quizName,inpercent,userName);

            Bundle bundle = new Bundle();
            bundle.putInt("ScoreInt",score);
            bundle.putSerializable("QnList",allthequestions);
            bundle.putSerializable("StudentInput",input);

            QuizResultsFrag tobestarted = new QuizResultsFrag();
            SessionActivity myActivity = (SessionActivity) context;

            tobestarted.setArguments(bundle);
            myActivity.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.profsessionhere,tobestarted).commit();


        }
    };
    

}
