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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.Database.Progress.NewQuizScoresDO;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.addEditFaq.AddEditFaqActivity;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 1002215 on 27/3/18.
 */

public class NameListFragment extends Fragment implements ProgressContract.View {

    @Override
    public void showProgress(ArrayList<Double> scoreList) {

    }

    @Override
    public void showAverage() {

    }

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private ProgressContract.Presenter mPresenter = new ProgressPresenter(this);
    private LinearLayout mNameListView;
    private RecyclerView nameListRecycler;
    private SwipeRefreshLayout swipeLayout;

    private NameListAdapter mNameListAdapter;

    public NameListFragment() {
        // Required empty public constructor
    }

    public static NameListFragment newInstance() {
        return new NameListFragment();
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
    public void setPresenter(@NonNull ProgressContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.name_list_main, container, false);
        nameListRecycler = (RecyclerView) view.findViewById(R.id.name_list_rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        nameListRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.name_list_swipe);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadNames(); // change it to load namelist
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.name_list_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddEditFaqActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void showNames(ArrayList<String> nameList) {

//        Log.i("NameListFragment", "showNames: " + nameList.get(0));
        mNameListAdapter = new NameListAdapter(nameList, mItemListener);
        nameListRecycler.setAdapter(mNameListAdapter);

    }

    public void showNoName() {
    }

    public void showLoadNameError() {

    }

    public void nameLoaded() {
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
    }

    NameListItemListener mItemListener = new NameListItemListener() {
        @Override
        public void onUpvoteClick(Faq clickedFaq) {
//            mPresenter.upvoteFaq(clickedFaq);
        }

        @Override
        public void onDownvoteClick(Faq clickedFaq) {
//            mPresenter.downvoteFaq(clickedFaq);
        }

        @Override
        public void onRetryClick() {
//            mPresenter.loadFaq();
        }


    };

    // TO BE REMOVED (cant remove yet due to QuestionsFragment using it)
    public class FaqJsonData {

        String question;
        String answer;
        String upvotes;

    }
}

