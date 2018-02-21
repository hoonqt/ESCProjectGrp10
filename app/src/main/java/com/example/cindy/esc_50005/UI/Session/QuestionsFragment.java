package com.example.cindy.esc_50005.UI.Session;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqAdapter;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqContract;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by 1002215 on 20/2/18.
 */

public class QuestionsFragment extends android.support.v4.app.Fragment implements QuestionsContract {

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;

    QuestionsJsonData[] questionsJsonData;
    private QuestionsAdapter mQuestionsAdapter;

    ArrayList<QuestionsJsonData> QuestionsList;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parseJson();
        View view=inflater.inflate(R.layout.ques_main, container, false);

        final EditText editText = (EditText)view.findViewById(R.id.question_input);
        Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.getText().toString();
                editText.setText("");
                Toast.makeText(getActivity(), "Your question has been added!", Toast.LENGTH_SHORT).show();

            }
        });

        RecyclerView questionsListRecycler=(RecyclerView) view.findViewById(R.id.recyclerViewQuestions);
        mLayoutManager= new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        questionsListRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mQuestionsAdapter=new QuestionsAdapter(getContext(),questionsJsonData);
        questionsListRecycler.setAdapter(mQuestionsAdapter);

        return view;
    }

    public void AddItemsToRecyclerViewArrayList(){

        QuestionsList = new ArrayList<>();
    }

    public class QuestionsJsonData {

        String question;
        String answer;
        String upvotes;

    }
    private String readTxt(int resource) {

        InputStream inputStream = getResources().openRawResource(resource);
        //TODO 3.2 Complete readTxt to take in a resource ID of a file,
        //          read it and return it as a single string
        // Reads an InputStream and converts it to a String.

        String line;
        String output="";

        try{
            BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"));
            while( (line = reader.readLine()) != null){
                output = output + line;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return output;

    }

    void parseJson() {

        Gson gson = new Gson();
        //Gson is a Java library that can be used to convert Java Objects into their JSON representation.
        // It can also be used to convert a JSON string to an equivalent Java object.
        //TODO 3.3 Invoke readTxt
        String myJsonData=readTxt(R.raw.faq);
        //TODO 3.4 parse the JSON file
        questionsJsonData=gson.fromJson(myJsonData, QuestionsJsonData[].class);
    }


}