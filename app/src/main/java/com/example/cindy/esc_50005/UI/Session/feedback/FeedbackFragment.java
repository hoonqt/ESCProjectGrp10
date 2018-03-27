package com.example.cindy.esc_50005.UI.Session.feedback;

import android.os.Bundle;
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
import android.widget.LinearLayout;

import com.example.cindy.esc_50005.Database.feedback.Feedback;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Session.feedbackDialog.FeedbackDialog;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tan_j on 21/3/2018.
 */


public class FeedbackFragment extends Fragment implements FeedbackContract.View {

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private FeedbackContract.Presenter mPresenter = new FeedbackPresenter(this);
    private LinearLayout mFeedbackView;
    private RecyclerView feedbackListRecycler;
    private SwipeRefreshLayout swipeLayout;

    private FeedbackAdapter mFeedbackAdapter;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        feedbackListRecycler = (RecyclerView) view.findViewById(R.id.feedback_rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        feedbackListRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.feedback_swipe);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadFeedback();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.feedback_fab);
        fab.setOnClickListener(new View.OnClickListener() {
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
        feedbackDialogFragment.show(fm,"tag");
    }

    public void showFeedback(ArrayList<Feedback> feedbackList) {

        mFeedbackAdapter = new FeedbackAdapter(feedbackList);
        feedbackListRecycler.setAdapter(mFeedbackAdapter);
    }

    public void feedbackLoaded() {
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
    }
}
