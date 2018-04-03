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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Main.SessionActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizEditor extends Fragment {

    private RecyclerView quizRecycler;

    private QuizEditor.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    SharedPreferences sharedPreferences;
    private ArrayList<QuizQuestions2DO> dataset;
    private QuizRemoteDataSource dataSource;

    EditText question;
    EditText option1;
    EditText option2;
    EditText option3;
    EditText option4;

    RadioButton button1;
    RadioButton button2;
    RadioButton button3;
    RadioButton button4;

    RadioGroup RadioGrp;

    String courseCode;
    String sessionID;

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
        courseCode = sharedPreferences.getString("CurrentCourseActivity", null).split(" ")[0];
        sessionID = sharedPreferences.getString("SessionSelected",null).split("-")[1].trim();

        dataSource = new QuizRemoteDataSource();

        question = (EditText) view.findViewById(R.id.questionBox);
        option1 = (EditText) view.findViewById(R.id.option1ans);
        option2 = (EditText) view.findViewById(R.id.option2ans);
        option3 = (EditText) view.findViewById(R.id.option3ans);
        option4 = (EditText) view.findViewById(R.id.option4ans);



        RadioGrp = (RadioGroup) view.findViewById(R.id.radiobuttons);

        button1 = (RadioButton)view.findViewById(R.id.option1);
        button2 = (RadioButton)view.findViewById(R.id.option2);
        button3 = (RadioButton)view.findViewById(R.id.option3);
        button4 = (RadioButton)view.findViewById(R.id.option4);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            dataset = (ArrayList<QuizQuestions2DO>)bundle.getSerializable("alltheqns");

            if (bundle.containsKey("index")) {
                index = bundle.getInt("index");
                QuizQuestions2DO qntobeedited = dataset.get(index);
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

                boolean state = questionAdder();

                if (state) {
                    EditQnListFrag adder = new EditQnListFrag();
                    Bundle bundler = new Bundle();

                    ArrayList<QuizQuestions2DO> tobetransferred = dataset;
                    Log.i("Hello world",dataset.toString());
                    bundler.putSerializable("allthequestions",tobetransferred);

                    adder.setArguments(bundler);

                    SessionActivity myActivity = (SessionActivity) getContext();
                    myActivity.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.profsessionhere,adder).addToBackStack(null).commit();
                }



            }
        });

        Button addqn = (Button)view.findViewById(R.id.addbtn);
        addqn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean state = questionAdder();
                if (state) {
                    question.setText("");
                    option1.setText("");
                    option2.setText("");
                    option3.setText("");
                    option4.setText("");
                    RadioGrp.clearCheck();
                }
            }
        });

        return view;
    }

    public boolean questionAdder() {

        ArrayList<String> options = new ArrayList<>();
        options.add(option1.getText().toString());
        options.add(option2.getText().toString());
        options.add(option3.getText().toString());
        options.add(option4.getText().toString());

        QuizQuestions2DO tobeadded = new QuizQuestions2DO();

        tobeadded.setQuestion(question.getText().toString());
        tobeadded.setOptions(options);
        tobeadded.setQuizNameQnID("Quiz 1" + " " + getSaltString());


        tobeadded.setSubjectCodeSessionCode(courseCode+sessionID);

        QuizRemoteDataSource adder = new QuizRemoteDataSource();

        int selectedID = RadioGrp.getCheckedRadioButtonId();

        Log.i("Selected input",Integer.toString(selectedID));

        if (button1.isChecked()) {

            tobeadded.setCorrectans(0.0);
            adder.putQuestion(tobeadded);
            Toast toast = Toast.makeText(getContext(),"Question added",Toast.LENGTH_SHORT);
            toast.show();
            dataset.add(tobeadded);
            dataSource.putQuestion(tobeadded);
            return true;

        }

        else if (button2.isChecked()) {
            tobeadded.setCorrectans(1.0);
            adder.putQuestion(tobeadded);
            Toast toast = Toast.makeText(getContext(),"Question added",Toast.LENGTH_SHORT);
            toast.show();
            dataset.add(tobeadded);
            dataSource.putQuestion(tobeadded);
            return true;
        }

        else if (button3.isChecked()) {
            tobeadded.setCorrectans(2.0);
            adder.putQuestion(tobeadded);
            Toast toast = Toast.makeText(getContext(),"Question added",Toast.LENGTH_SHORT);
            toast.show();
            dataset.add(tobeadded);
            dataSource.putQuestion(tobeadded);
            return true;
        }

        else if (button4.isChecked()) {
            tobeadded.setCorrectans(3.0);
            adder.putQuestion(tobeadded);
            Toast toast = Toast.makeText(getContext(),"Question added",Toast.LENGTH_SHORT);
            toast.show();
            dataset.add(tobeadded);
            dataSource.putQuestion(tobeadded);
            return true;
        }

        else {

            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setMessage("Multiple options selected." );
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            return false;

        }

    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


}






