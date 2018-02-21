package com.example.cindy.esc_50005.UI.Session;

import android.content.Context;
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
<<<<<<< HEAD
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

=======
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqAdapter;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqContract;
import com.google.gson.Gson;

>>>>>>> d9821ecc2426fdd040fb296cdd30d2c9c1c65aae
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> d9821ecc2426fdd040fb296cdd30d2c9c1c65aae

/**
 * Created by 1002215 on 20/2/18.
 */

public class QuestionsFragment extends android.support.v4.app.Fragment implements QuestionsContract, View.OnClickListener {

        private EditText editText;
        private Button btn;
        private JSONArray array = new JSONArray();
        private RecyclerView questionListRecycler;
        QuestionsFragment.QuestionsJsonData[] questionsJsonData;

        private enum LayoutManagerType {
            LINEAR_LAYOUT_MANAGER
        }

        protected com.example.cindy.esc_50005.UI.Session.QuestionsFragment.LayoutManagerType mCurrentLayoutManagerType;
        protected RecyclerView.LayoutManager mLayoutManager;

        com.example.cindy.esc_50005.UI.Course.FAQ.FaqFragment.FaqJsonData[] faqJsonData;
        private QuestionsAdapter mQuestionsAdapter;

        ArrayList<com.example.cindy.esc_50005.UI.Course.FAQ.FaqFragment.FaqJsonData> FaqList;

        public QuestionsFragment() {
            // Required empty public constructor
        }
        Context context;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.post_question_main, container, false);
            parseJson();
            btn = view.findViewById(R.id.add_button);
            editText = (EditText) view.findViewById(R.id.question_input);
            btn.setOnClickListener(this);
            questionListRecycler=(RecyclerView) view.findViewById(R.id.recyclerViewPostQuestions);
            mLayoutManager= new LinearLayoutManager(getActivity());
            mCurrentLayoutManagerType = com.example.cindy.esc_50005.UI.Session.QuestionsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
            questionListRecycler.setLayoutManager(new LinearLayoutManager(context));
            mQuestionsAdapter=new QuestionsAdapter(getContext(),questionsJsonData);
            questionListRecycler.setAdapter(mQuestionsAdapter);

            return view;
        }

        @Override
        public void onClick(View v) {
            String your_question = editText.getText().toString();
            JSONObject json = new JSONObject();
            try{
                json.put("question", your_question);
            }
            catch(Exception ex)
            {

            }
            array.put(json);
            parseJson();

        }

        public class QuestionsJsonData {

            String question;
            String upvotes;

        }
        private String readTxt(int resource) {

            InputStream inputStream = getResources().openRawResource(resource);
            //TODO 3.2 Complete readTxt to take in a resource ID of a file,
            //          read it and return it as a single string
            // Reads an InputStream and converts it to a String.

<<<<<<< HEAD
            String line;
            String output="";
=======
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;

    QuestionsJsonData[] questionsJsonData;
    private QuestionsAdapter mQuestionsAdapter;

    ArrayList<QuestionsJsonData> QuestionsList;
>>>>>>> d9821ecc2426fdd040fb296cdd30d2c9c1c65aae

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

<<<<<<< HEAD
        }

        void parseJson() {
            try{
                Gson gson = new Gson();
                StringBuilder string=new StringBuilder();
                string.append("[");
                //read each object of array with Json library
                for(int i=0; i<array.length(); i++){

                    //get the object
                    JSONObject jsonObject = array.getJSONObject(i);

                    //get string of object from Json library to convert it to real object with Gson library
                    string.append(jsonObject.toString());
                    if(i!=array.length()-1)
                    {
                        string.append(",");
                    }
=======
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
>>>>>>> d9821ecc2426fdd040fb296cdd30d2c9c1c65aae

                }
                string.append("]");
                Log.i("printString",string.toString());
                questionsJsonData=gson.fromJson(string.toString(), QuestionsFragment.QuestionsJsonData[].class);
                Log.i("questionsJsonData",Integer.toString(questionsJsonData.length));
                //return list with all generated objects
                mQuestionsAdapter=new QuestionsAdapter(getContext(),questionsJsonData);
                questionListRecycler.setAdapter(mQuestionsAdapter);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
}