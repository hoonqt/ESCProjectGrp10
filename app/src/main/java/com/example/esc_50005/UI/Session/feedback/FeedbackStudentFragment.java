package com.example.esc_50005.UI.Session.feedback;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.esc_50005.Database.feedback.Feedback;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.feedbackDialog.FeedbackDialog;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tan_j on 21/3/2018.
 */


public class FeedbackStudentFragment extends Fragment implements FeedbackContract.View {

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private FeedbackContract.Presenter mPresenter = new FeedbackPresenter(this);
    private SwipeRefreshLayout swipeLayout;
    private TextView tv_message;
    private Button btn_add;
    private FloatingActionButton fab;

    SharedPreferences userInformation;
    String sessionId;
    String userId;

    public FeedbackStudentFragment() {
        // Required empty public constructor
    }

    public static FeedbackStudentFragment newInstance() {
        return new FeedbackStudentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInformation = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        sessionId = userInformation.getString(getString(R.string.session_id), "");
        userId = userInformation.getString(getString(R.string.user_id), "");
        mPresenter.setSessionId(sessionId);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.session_fab);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull FeedbackContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_student, container, false);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.feedback_swipe);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadFeedback();
            }
        });

        btn_add = (Button) view.findViewById(R.id.feedback_btn_add);
        tv_message = (TextView) view.findViewById(R.id.feedback_tv_message);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFeedbackDialogUi();
            }
        });

        return view;
    }

    public void showFeedbackDialogUi() {
        FragmentManager fm = getFragmentManager();
        FeedbackDialog feedbackDialogFragment = FeedbackDialog.newInstance();
        feedbackDialogFragment.show(fm, "tag");
    }

    public void showFeedback(ArrayList<Feedback> feedbackList) {
        boolean gaveFeedback = false;
        for (Feedback fb : feedbackList) {
            if (fb.getStudentId().equals(userId)) {
                gaveFeedback = true;
                break;
            }
        }
        if (gaveFeedback) {
            btn_add.setVisibility(View.INVISIBLE);
            tv_message.setText("Thanks for your feedback!");
        } else {
            tv_message.setText("How was today's session?");
            btn_add.setVisibility(View.VISIBLE);
        }
    }

    public void feedbackLoaded() {
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
    }

    public void setFab() {
        fab.setVisibility(View.GONE);
    }
}
