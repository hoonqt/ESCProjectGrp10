package com.example.esc_50005.UI.ProfSession.SideScreens;


import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Main.SessionActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizEditor extends Fragment {

    private RecyclerView quizRecycler;

    private QuizEditor.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    SharedPreferences sharedPreferences;
    private ArrayList<QuizQuestions1DO> dataset;

    EditText question;
    EditText option1;
    EditText option2;
    EditText option3;
    EditText option4;

    RadioGroup RadioGrp;

    TextView submit;

    int index = 100;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public QuizEditor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_quiz_editor, container, false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = QuizEditor.LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.getString("50.004", null);

        question = (EditText) view.findViewById(R.id.questionBox);
        option1 = (EditText) view.findViewById(R.id.option1ans);
        option2 = (EditText) view.findViewById(R.id.option2ans);
        option3 = (EditText) view.findViewById(R.id.option3ans);
        option4 = (EditText) view.findViewById(R.id.option4ans);

        RadioGrp = (RadioGroup) view.findViewById(R.id.radiobuttons);

        final Bundle bundle = this.getArguments();

        if (bundle != null) {
            dataset = (ArrayList<QuizQuestions1DO>)bundle.getSerializable("allthequestions");

            if (bundle.containsKey("index")) {
                index = bundle.getInt("index");
                QuizQuestions1DO qntobeedited = dataset.get(index);
                question.setText(qntobeedited.getQuestion());
                option1.setText(qntobeedited.getOptions().get(0));
                option2.setText(qntobeedited.getOptions().get(1));
                option3.setText(qntobeedited.getOptions().get(2));
                option4.setText(qntobeedited.getOptions().get(3));
            }

        }

        Button subnmit = (Button)view.findViewById(R.id.submitbtn);
        subnmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                questionAdder();

                EditQnListFrag adder = new EditQnListFrag();
                Bundle bundler = new Bundle();

                ArrayList<QuizQuestions1DO> tobetransferred = dataset;
                bundler.putSerializable("allthequestions",tobetransferred);

                adder.setArguments(bundler);

                SessionActivity myActivity = (SessionActivity) getContext();
                myActivity.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.profsessionhere,adder).addToBackStack(null).commit();


            }
        });

        Button addqn = (Button)view.findViewById(R.id.addbtn);
        addqn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                questionAdder();
                question.setText("");
                option1.setText("");
                option2.setText("");
                option3.setText("");
                option4.setText("");
                RadioGrp.clearCheck();
            }
        });

        return view;
    }

    public void questionAdder() {

        ArrayList<String> options = new ArrayList<>();
        options.add(option1.getText().toString());
        options.add(option2.getText().toString());
        options.add(option3.getText().toString());
        options.add(option4.getText().toString());

        QuizQuestions1DO tobeadded = new QuizQuestions1DO();

        tobeadded.setQuestion(question.getText().toString());
        tobeadded.setOptions(options);
        tobeadded.setQuizName("Quiz 1");
        tobeadded.setSubjectCodeSessionCode("50.004Session2");

        QuizRemoteDataSource adder = new QuizRemoteDataSource();

        int selectedID = RadioGrp.getCheckedRadioButtonId();

        Log.i("Selected input",Integer.toString(selectedID));

        if (selectedID == R.id.option1) {

            tobeadded.setCorrectans(0.0);
            adder.putQuestion(tobeadded);
            Toast toast = Toast.makeText(getContext(),"Question added",Toast.LENGTH_SHORT);
            toast.show();
            dataset.add(tobeadded);

        }

        else if (selectedID == R.id.option2) {
            tobeadded.setCorrectans(1.0);
            adder.putQuestion(tobeadded);
            Toast toast = Toast.makeText(getContext(),"Question added",Toast.LENGTH_SHORT);
            toast.show();
            dataset.add(tobeadded);
        }

        else if (selectedID == R.id.option3) {
            tobeadded.setCorrectans(2.0);
            adder.putQuestion(tobeadded);
            Toast toast = Toast.makeText(getContext(),"Question added",Toast.LENGTH_SHORT);
            toast.show();
            dataset.add(tobeadded);
        }

        else if (selectedID == R.id.option4) {
            tobeadded.setCorrectans(3.0);
            adder.putQuestion(tobeadded);
            Toast toast = Toast.makeText(getContext(),"Question added",Toast.LENGTH_SHORT);
            toast.show();
            dataset.add(tobeadded);
        }

        else {

            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setMessage("Wrong username or password sorry! " );
            AlertDialog alertDialog=builder.create();
            alertDialog.show();

        }


    }


}






