package com.example.esc_50005.UI.Course.FAQ;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.Log;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by 1002215 on 27/3/18.
 */

public class NameListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final String TAG = "NameListAdapter";

    private ArrayList<String> mNameList;
    private ArrayList<String> mStudentIdsList;
    private NameListItemListener mNameListItemListener;
    private ArrayList<Double> avgList;
    private ArrayList<String> mStudentNamesList;

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private static int viewHolderCount = 0;

    public NameListAdapter(ArrayList<String> nameList, ArrayList<String> studentIdsList, ArrayList<Double> avgList, NameListItemListener itemListener){
        this.mNameList = nameList;
        this.avgList = avgList;
        this.mStudentIdsList = studentIdsList;
        this.mNameListItemListener = itemListener;
    }


    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(position);
    }

    //indicates how many list objects it has

    public int getItemCount() {
        android.util.Log.i(TAG,  "here2" );
        if (mNameList == null || mNameList.size() == 0) {
            return 1;
        } else {
            return mNameList.size();
        }
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;
        android.util.Log.i(TAG,  "viewtype" + viewType );
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                view = inflater.inflate(R.layout.name_list_recycler,parent,shouldAttachToParentImmediately);
                return new NameListViewHolder(view);
            case VIEW_TYPE_EMPTY:
                view = inflater.inflate(R.layout.item_empty_view,parent,shouldAttachToParentImmediately);
                return new EmptyViewHolder(view);
            default:
                view = inflater.inflate(R.layout.name_list_recycler,parent,shouldAttachToParentImmediately);
                return new NameListViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mNameList != null && mNameList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    class NameListViewHolder extends BaseViewHolder implements  View.OnClickListener {

        TextView tv_question;
        TextView tv_answer;
        TextView tv_upvote;
        TextView tv_time;
        Button arrow;
        boolean upvoted;

        NameListViewHolder(View v) {

            super(v);
            //we need the v object because the view contains the references to the widgets that we need
            tv_question = (TextView) v.findViewById(R.id.name_list_tv_question);
//            tv_answer = (TextView) v.findViewById(R.id.name_list_tv_answer);
//            tv_upvote = (TextView) v.findViewById(R.id.name_list_tv_upvote);
            tv_time = (TextView) v.findViewById(R.id.name_list_tv_time);
            arrow = (Button) v.findViewById(R.id.name_list_progress_btn);

            v.setOnClickListener(this);
            arrow.setOnClickListener(this);

        }

        public void bind(int position) {
            Log.i(TAG, "NameList size: : " + mNameList.size());
            Log.i(TAG, "avgList size: : " + avgList.size());

            String name = mNameList.get(position);
            Double avg = avgList.get(position);
            if(avg < 50){
                tv_question.setText(name);
//            tv_answer.setText(name);
//            tv_upvote.setText(String.valueOf(faq.getUpvotes()));
                tv_time.setText("Average Score: " + Double.toString(avg));
                tv_time.setTextColor(Color.parseColor("#E1595C"));
            } else{
                tv_question.setText(name);
                tv_time.setText("Average Score: " + Double.toString(avg));
            }


//            upvoted = userUpvoted(faq);
//            if (upvoted) {
//                btn_upvote.setText("Downvote");
//            } else {
//                btn_upvote.setText("Upvote");
//            }
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == arrow.getId()) {
                    Toast.makeText(v.getContext(), "Id = " + mStudentIdsList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    mNameListItemListener.onArrowClick(mStudentIdsList.get(getAdapterPosition()),mNameList.get(getAdapterPosition()));
            }

        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements View.OnClickListener {

        Button btn_retry;
        TextView tv_title;
        TextView tv_message;

        public EmptyViewHolder(View view) {
            super(view);
            btn_retry = view.findViewById(R.id.empty_btn_retry);
            tv_title = view.findViewById(R.id.empty_tv_title);
            tv_message = view.findViewById(R.id.empty_tv_message);

            btn_retry.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == btn_retry.getId()) {
                mNameListItemListener.onRetryClick();
            }
        }
    }
}
