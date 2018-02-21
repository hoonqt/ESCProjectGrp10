package com.example.cindy.esc_50005.UI.Session;

import android.app.AlertDialog;
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
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


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
            try {
                Gson gson = new Gson();
                StringBuilder string = new StringBuilder();
                string.append("[");
                //read each object of array with Json library
                for (int i = 0; i < array.length(); i++) {

                    //get the object
                    JSONObject jsonObject = array.getJSONObject(i);

                    //get string of object from Json library to convert it to real object with Gson library
                    string.append(jsonObject.toString());
                    if (i != array.length() - 1) {
                        string.append(",");
                    }
                }


                string.append("]");
                questionsJsonData=gson.fromJson(string.toString(), QuestionsFragment.QuestionsJsonData[].class);
                mQuestionsAdapter=new QuestionsAdapter(getContext(),questionsJsonData);
                questionListRecycler.setAdapter(mQuestionsAdapter);
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

                builder.setMessage("Question has been successfully posted! " );

                AlertDialog alertDialog=builder.create();
                //instantiates the object
                alertDialog.show();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
}