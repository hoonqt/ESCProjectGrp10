package com.example.esc_50005.UI.Course.FAQ;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.Database.utilities.Injection;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Course.FAQ.editFaq.EditFaqDialog;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class FaqFragment extends Fragment implements FaqContract.View {

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private FaqContract.Presenter mPresenter;
//    private FaqContract.Presenter mPresenter = new FaqPresenter(this);
    private LinearLayout mFaqView;
    private RecyclerView faqListRecycler;
    private SwipeRefreshLayout swipeLayout;
    private CoordinatorLayout coordinatorLayout;

    private SharedPreferences userInformation;
    private String userType;
    private String userId;
    private String courseId;

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
        mPresenter = new FaqPresenter(
                Injection.provideFaqRepository(getActivity().getApplicationContext()), this);

        userInformation = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        userType = userInformation.getString("UserType","");
        userId = userInformation.getString("Username","");
        courseId = userInformation.getString("CurrentCourseActivity", "");
        mPresenter.setUserId(userId);
        mPresenter.setCourseId(courseId);
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

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.faq_cl);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.faq_fab);

        if (userType.equals("student")) {
//            fab.setVisibility(View.GONE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), AddEditFaqActivity.class);
//                startActivity(intent);
                FragmentManager fm = getFragmentManager();
                EditFaqDialog editFaqDialogFragment = new EditFaqDialog();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.add(R.id.course_rl, editFaqDialogFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }

    public void showFaq(ArrayList<Faq> faqList) {

        mFaqAdapter = new FaqAdapter(faqList, mItemListener, userId);
        faqListRecycler.setAdapter(mFaqAdapter);
    }

    public void showNoFaq() {
    }

    public void showLoadFaqError() {
        ConnectivityManager conMan = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (mobile != NetworkInfo.State.CONNECTED && wifi != NetworkInfo.State.CONNECTED) {
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "No Connection", Snackbar.LENGTH_LONG)
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mPresenter.loadFaq();
                        }
                    });
            snackbar.show();
//            snackbar.setActionTextColor(Color.WHITE);
        }
    }

    public void faqLoaded() {
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
        mFaqAdapter.notifyDataSetChanged();
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
