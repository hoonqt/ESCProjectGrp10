package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.addEditFaq.AddEditFaqActivity;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class FaqFragment extends Fragment implements FaqContract.View {

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private FaqContract.Presenter mPresenter = new FaqPresenter(this);
    private LinearLayout mFaqView;
    private RecyclerView faqListRecycler;
    private SwipeRefreshLayout swipeLayout;

    private FaqAdapter mFaqAdapter;

    public FaqFragment() {
        // Required empty public constructor
    }

    public static FaqFragment newInstance() {
        return new FaqFragment();
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
    public void setPresenter(@NonNull FaqContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faq_main, container, false);
        faqListRecycler = (RecyclerView) view.findViewById(R.id.faq_rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        faqListRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.faq_swipe);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadFaq();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.faq_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddEditFaqActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void showFaq(ArrayList<Faq> faqList) {

        mFaqAdapter = new FaqAdapter(faqList, mItemListener);
        faqListRecycler.setAdapter(mFaqAdapter);
    }

    public void showNoFaq() {
    }

    public void showLoadFaqError() {

    }

    public void faqLoaded() {
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
    }

    FaqItemListener mItemListener = new FaqItemListener() {
        @Override
        public void onUpvoteClick(Faq clickedFaq) {
            mPresenter.upvoteFaq(clickedFaq);
        }

        @Override
        public void onDownvoteClick(Faq clickedFaq) {
            mPresenter.downvoteFaq(clickedFaq);
        }

        @Override
        public void onRetryClick() {
            mPresenter.loadFaq();
        }


    };

    // TO BE REMOVED (cant remove yet due to QuestionsFragment using it)
    public class FaqJsonData {

        String question;
        String answer;
        String upvotes;

    }
}
