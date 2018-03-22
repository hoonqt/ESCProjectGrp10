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

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;
import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.addEditFaq.AddEditFaqActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * A simple {@link Fragment} subclass.
 */

public class ProgressFragment extends Fragment implements ProgressContract.View {

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    private final String TAG = "ProgressFragment";

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private ProgressContract.Presenter mPresenter = new ProgressPresenter(this);
    private LinearLayout mFaqView;
    private RecyclerView faqListRecycler;
    private SwipeRefreshLayout swipeLayout;
    private BarChart mChart;


    private FaqAdapter mFaqAdapter;

    public ProgressFragment() {
        // Required empty public constructor
    }

    public static ProgressFragment newInstance() {
        return new ProgressFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        Log.i(TAG, "onResume");

    }

    @Override
    public void setPresenter(@NonNull ProgressContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
        Log.i(TAG, "setPresenter");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_progress_main, container, false);
        mChart = (BarChart)view.findViewById(R.id.chart1);
//        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.faq_swipe);
//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.loadScores();
//            }
//        });
        Log.i(TAG, "onCreateView");


        return view;
    }

        private void setData(int count){
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

        for(int i=0; i<count; i++){
            float value = (i * 20 );
            yVals.add(new BarEntry(i,value));
        }

        BarDataSet set = new BarDataSet(yVals, "(Quizzes)");
        set.setDrawIcons(false);
        set.setColors(ColorTemplate.LIBERTY_COLORS);
        //Show value on top of each bar
        set.setDrawValues(true);
        set.setValueTextSize(15);

        BarData data = new BarData(set);
        data.setValueFormatter(new MyValueFormatter());
        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(500);
            Log.i(TAG, "setData");

        }



    @Override
    public void showProgress(ArrayList<Double> scoreList) {

            Log.i(TAG, "hi" + scoreList.size());

        mChart.getDescription().setEnabled(false);
        mChart.setMaxVisibleValueCount(40);

        final String[] ds = new String[5];
        ds[0]="Quiz 1";
        ds[1]="Quiz 2";
        ds[2]="Quiz 3";
        ds[3]="Quiz 4";
        ds[4]="Quiz 5";


        XAxis xval = mChart.getXAxis();
        xval.setDrawLabels(true);
        xval.setValueFormatter(new MyAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.i("zain", "value " + value);
                return ds[Math.round(value)];
            }

//            @Override
//            public int getDecimalDigits() {
//                return 0;
//            }
        });

        setData(5);
        mChart.setFitBars(true);

//        SessionQuestionsRemoteDataSource session= new SessionQuestionsRemoteDataSource();
//        session.addQuestion("What is the difference between Observer and Strategy Design Pattern?","111");

    }

    @Override
    public void showAverage() {

    }

    // TO BE REMOVED (cant remove yet due to QuestionsFragment using it)
    public class FaqJsonData {

        String question;
        String answer;
        String upvotes;

    }
}
//public class ProgressFragment extends Fragment implements ProgressContract.View {
//
//    private SessionQuestionsRemoteDataSource progressRepository= new SessionQuestionsRemoteDataSource();
//    private ProgressContract.Presenter mPresenter = new ProgressPresenter(progressRepository,this);
//    private BarChart mChart;
//
//    public ProgressFragment() {
//        // Required empty public constructor
//    }
//
//    public static ProgressFragment newInstance() {
//        return new ProgressFragment();
//    }
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mPresenter.start();
//    }
//
//    @Override
//    public void setPresenter(@NonNull ProgressContract.Presenter presenter) {
//        mPresenter = checkNotNull(presenter);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.my_progress_main, container, false);
//        mChart = (BarChart)view.findViewById(R.id.chart1);
//
//        return view;
//    }
//
//    private void setData(int count){
//        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
//
//        for(int i=0; i<count; i++){
//            float value = (i * 20 );
//            yVals.add(new BarEntry(i,value));
//        }
//
//        BarDataSet set = new BarDataSet(yVals, "(Quizzes)");
//        set.setDrawIcons(false);
//        set.setColors(ColorTemplate.LIBERTY_COLORS);
//        //Show value on top of each bar
//        set.setDrawValues(true);
//        set.setValueTextSize(15);
//
//        BarData data = new BarData(set);
//        data.setValueFormatter(new MyValueFormatter());
//        mChart.setData(data);
//        mChart.invalidate();
//        mChart.animateY(500);
//    }
//
//
//
//    @Override
//    public <T> void showProgress(T data) {
//
//        mChart.getDescription().setEnabled(false);
//        mChart.setMaxVisibleValueCount(40);
//
//        final String[] ds = new String[5];
//        ds[0]="Quiz 1";
//        ds[1]="Quiz 2";
//        ds[2]="Quiz 3";
//        ds[3]="Quiz 4";
//        ds[4]="Quiz 5";
//
//
//        XAxis xval = mChart.getXAxis();
//        xval.setDrawLabels(true);
//        xval.setValueFormatter(new MyAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                Log.i("zain", "value " + value);
//                return ds[Math.round(value)];
//            }
//
////            @Override
////            public int getDecimalDigits() {
////                return 0;
////            }
//        });
//
//        setData(5);
//        mChart.setFitBars(true);
//
////        SessionQuestionsRemoteDataSource session= new SessionQuestionsRemoteDataSource();
////        session.addQuestion("What is the difference between Observer and Strategy Design Pattern?","111");
//
//    }
//
//    @Override
//    public void showAverage() {
//
//    }
//
//}
