package com.example.esc_50005.UI.Session.Student;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
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
import com.example.esc_50005.Database.utilities.Injection;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Main.QuestionsAdapter;
import com.example.esc_50005.UI.Session.Main.QuestionsContract;
import com.example.esc_50005.UI.Session.Main.QuestionsItemListener;
import com.example.esc_50005.UI.Session.Main.QuestionsPresenter;
import com.example.esc_50005.UI.Session.addQuestionDialog.AddQuestionDialog;

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
    private FloatingActionButton fab;

    private SharedPreferences userInformation;
    private String userType;
    private String userId;
    private String sessionId;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new QuestionsPresenter(this);
//                Injection.provideFaqRepository(getActivity().getApplicationContext()), this);

        userInformation = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        userType = userInformation.getString(getString(R.string.user_type),"");
        userId = userInformation.getString(getString(R.string.user_id),"");
        sessionId = userInformation.getString(getString(R.string.session_id), "");
        mPresenter.setUserId(userId);
        mPresenter.setSessionId(sessionId);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFabClick();
            }
        });
    }

    @Override
    public void setPresenter(@NonNull QuestionsContract.Presenter presenter) {
        Log.i("set presenter", "set presenter");
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("onCreateView", "onCreateView");
        View view = inflater.inflate(R.layout.sessionquestions_postquestions_main, container, false);

        questionListRecycler = view.findViewById(R.id.questions_rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = QuestionsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        questionListRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.questions_swipe);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadQuestions();
            }
        });
        fab = (FloatingActionButton) getActivity().findViewById(R.id.session_fab);

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
        mQuestionsAdapter = new QuestionsAdapter(questionsList, mItemListener, userId);
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

        @Override
        public void onDownvoteClick(SessionQuestionsDO clickedQuestion) {
            mPresenter.downvoteQuestion(clickedQuestion);
        }

        @Override
        public void onRetryClick() {
            mPresenter.loadQuestions();
        }
    };

    public void questionsLoaded() {
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
        mQuestionsAdapter.notifyDataSetChanged();
    }

    public void setFab() {
        if (userType.equals("professor")) {
            fab.setVisibility(View.GONE);
        } else {
            fab.setVisibility(View.VISIBLE);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFabClick();
            }
        });
    }

    public void onFabClick() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                AddQuestionDialog addQuestionDialogFragment = new AddQuestionDialog();
                addQuestionDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
                addQuestionDialogFragment.show(fm, "fragment_alert");

            }
        });
    }
}