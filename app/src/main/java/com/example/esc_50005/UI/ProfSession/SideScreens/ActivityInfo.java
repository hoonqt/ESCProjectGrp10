package com.example.esc_50005.UI.ProfSession.SideScreens;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Main.SessionActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityInfo extends Fragment{
    private Context context;
    private ArrayList<QuizQuestions2DO> dataset;


    public ActivityInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        context = getContext();

        View view = inflater.inflate(R.layout.fragment_activity_viewer, container, false);
        TextView editbtn = view.findViewById(R.id.editbutton);
        editbtn.setOnClickListener(btnListener);
        TextView gradesbtn = view.findViewById(R.id.gradesbutton);
        ImageView edit1 = view.findViewById(R.id.imageView1);
        ImageView edit2 = view.findViewById(R.id.imageView2);
        edit1.setOnClickListener(btnListener);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            dataset = (ArrayList<QuizQuestions2DO>)bundle.getSerializable("allthequestions");
        }


        return view;
    }



    private View.OnClickListener btnListener = new View.OnClickListener()
    {

        public void onClick(View v) {

            EditQnListFrag adder = new EditQnListFrag();
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("allthequestions",dataset);
            adder.setArguments(bundle2);
            SessionActivity myActivity = (SessionActivity) context;
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.profsessionhere,adder).addToBackStack(null).commit();
        }

    };
}
