package com.example.esc_50005.UI.Session.Student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Main.QuestionsAdapter;
import com.example.esc_50005.UI.Session.Main.QuestionsContract;
import com.example.esc_50005.UI.Session.Main.QuestionsItemListener;
import com.example.esc_50005.UI.Session.Main.QuestionsPresenter;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;


public class QuestionsFragment extends android.support.v4.app.Fragment implements QuestionsContract.View, View.OnClickListener {

    private EditText editText;
    private Button btn;
    private RecyclerView questionListRecycler;

    private QuestionsFragment.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private QuestionsContract.Presenter mPresenter = new QuestionsPresenter(this);
    private QuestionsAdapter mQuestionsAdapter;
    private LinearLayout mFaqView;
    private RecyclerView faqListRecycler;
    private SwipeRefreshLayout swipeLayout;

    ArrayList<com.example.esc_50005.UI.Course.FAQ.FaqFragment.FaqJsonData> FaqList;

    private enum LayoutManagerType {
            LINEAR_LAYOUT_MANAGER
        }

    public QuestionsFragment() {
            // Required empty public constructor
        }

    public static QuestionsFragment newInstance() {
        return new QuestionsFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull QuestionsContract.Presenter presenter) {
        Log.i("set presenter","set presenter");
        mPresenter = checkNotNull(presenter);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.i("onCreateView","onCreateView");
            View view=inflater.inflate(R.layout.sessionquestions_postquestions_main, container, false);
            btn = view.findViewById(R.id.add_button);
            editText = (EditText) view.findViewById(R.id.question_input);
            btn.setOnClickListener(this);
            questionListRecycler=view.findViewById(R.id.recyclerViewPostQuestions);
            mLayoutManager= new LinearLayoutManager(getActivity());
            mCurrentLayoutManagerType = QuestionsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
            questionListRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

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
    public void showAddedQuestion(ArrayList<SessionQuestionsDO> questionsList) {
        mQuestionsAdapter=new QuestionsAdapter(questionsList,mItemListener);
        questionListRecycler.setAdapter(mQuestionsAdapter);

    }

    @Override
    public void showNoQuestions() {

    }

    @Override
    public void showLoadQuestionsError() {

    }

    QuestionsItemListener mItemListener = new QuestionsItemListener() {
        @Override
        public void onUpvoteClick(SessionQuestionsDO clickedQuestion) {
            mPresenter.upvoteQuestion(clickedQuestion);
        }
    };

}