package com.example.cindy.esc_50005.UI.Session;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;
import com.example.cindy.esc_50005.R;

import org.json.JSONArray;

import java.util.ArrayList;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 1002215 on 20/2/18.
 */

public class QuestionsFragment extends android.support.v4.app.Fragment implements QuestionsContract.View, View.OnClickListener {


    private EditText editText;
    private Button btn;
    private JSONArray array = new JSONArray();
    private RecyclerView questionListRecycler;
    QuestionsJsonData[] questionsJsonData;

    private QuestionsFragment.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private SessionQuestionsRemoteDataSource sessionQuestionsRepository= new SessionQuestionsRemoteDataSource();
    private QuestionsContract.Presenter mPresenter = new QuestionsPresenter(sessionQuestionsRepository, this);

    com.example.cindy.esc_50005.UI.Course.FAQ.FaqFragment.FaqJsonData[] faqJsonData;
    private QuestionsAdapter mQuestionsAdapter;

    ArrayList<com.example.cindy.esc_50005.UI.Course.FAQ.FaqFragment.FaqJsonData> FaqList;

    private enum LayoutManagerType {
            LINEAR_LAYOUT_MANAGER
        }

    public QuestionsFragment() {
            // Required empty public constructor
        }
        Context context;

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull QuestionsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.sessionquestions_postquestions_main, container, false);
            btn = view.findViewById(R.id.add_button);
            editText = (EditText) view.findViewById(R.id.question_input);
            btn.setOnClickListener(this);
            questionListRecycler=(RecyclerView) view.findViewById(R.id.recyclerViewPostQuestions);
            mLayoutManager= new LinearLayoutManager(getActivity());
            mCurrentLayoutManagerType = QuestionsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
            questionListRecycler.setLayoutManager(new LinearLayoutManager(context));
//            mQuestionsAdapter=new QuestionsAdapter(getContext(),questionsJsonData);
//            questionListRecycler.setAdapter(mQuestionsAdapter);

            return view;
        }

    @Override
    public void onClick(View v) {
            String question = editText.getText().toString();
            mPresenter.addNewQuestion(question);
            editText.setText("");

        }

    @Override
    public void showQuestions() {

    }


    @Override
    public <T> void showAddedQuestion(T data) {
//        mPresenter.loadQuestions();
//
//        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
//
//        builder.setMessage("Question has been successfully posted! " );
//
//        AlertDialog alertDialog=builder.create();
//        //instantiates the object
//        alertDialog.show();


        Log.i("data",data.toString());
        mQuestionsAdapter=new QuestionsAdapter(getContext(),data);
        questionListRecycler.setAdapter(mQuestionsAdapter);

    }

    @Override
    public void showNoQuestions() {

    }

    @Override
    public void showLoadQuestionsError() {

    }

    public class QuestionsJsonData {

            String question;
            String upvotes;

        }
}