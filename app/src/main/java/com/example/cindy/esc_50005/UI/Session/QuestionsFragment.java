package com.example.cindy.esc_50005.UI.Session;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqContract;

/**
 * Created by 1002215 on 20/2/18.
 */

public class QuestionsFragment extends android.support.v4.app.Fragment implements QuestionsContract {


    public QuestionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.ques_recycler, container, false);
//        Button addButton = (Button)rootView.findViewById(R.id.add_button);
//        addButton.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v){
//                EditText editText = (EditText)rootView.findViewById(R.id.question_input);
//                editText.setText("");
//
//            }
//        });
        // Inflate the layout for this fragment
        return rootView;


    }


}