package com.example.cindy.esc_50005.UI.Course.FAQ;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cindy.esc_50005.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    private TextView textbox;


    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        textbox = view.findViewById(R.id.quizQuestionBox);

        // Inflate the layout for this fragment
        return view;
    }

}
