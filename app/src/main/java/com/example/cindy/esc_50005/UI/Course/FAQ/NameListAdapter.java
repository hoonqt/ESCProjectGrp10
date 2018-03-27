package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.Database.Progress.NewQuizScoresDO;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by 1002215 on 27/3/18.
 */

public class NameListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final String TAG = "NameListAdapter";

    private ArrayList<NewQuizScoresDO> mNameList;
    private NameListItemListener mNameListItemListener;

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private static int viewHolderCount = 0;

    public NameListAdapter(ArrayList<NewQuizScoresDO> nameList, NameListItemListener itemListener){
        this.mNameList = nameList;
        this.mNameListItemListener = itemListener;
    }


    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(position);
    }

    //indicates how many list objects it has

    public int getItemCount() {
        if (mNameList == null) {
            return 0;
        } else {
            return mNameList.size();
        }
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;
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
        Button btn_upvote;
        boolean upvoted;

        NameListViewHolder(View v) {

            super(v);
            //we need the v object because the view contains the references to the widgets that we need
            tv_question = (TextView) v.findViewById(R.id.name_list_tv_question);
            tv_answer = (TextView) v.findViewById(R.id.name_list_tv_answer);
            tv_upvote = (TextView) v.findViewById(R.id.name_list_tv_upvote);
            tv_time = (TextView) v.findViewById(R.id.name_list_tv_time);
            btn_upvote = (Button) v.findViewById(R.id.name_list_btn_upvote);

            v.setOnClickListener(this);
            btn_upvote.setOnClickListener(this);

        }

        public void bind(int position) {

            Faq faq = mFaqList.get(position);
            tv_question.setText(faq.getQuestion());
            tv_answer.setText(faq.getAnswer());
            tv_upvote.setText(String.valueOf(faq.getUpvotes()));
            tv_time.setText(faq.getAuthor() + ", " + faq.getDate());

            upvoted = userUpvoted(faq);
            if (upvoted) {
                btn_upvote.setText("Downvote");
            } else {
                btn_upvote.setText("Upvote");
            }
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == btn_upvote.getId()) {
                if (!upvoted) {
                    Toast.makeText(v.getContext(), "False = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    Log.i(TAG,"upvote false");
                    mFaqItemListener.onUpvoteClick(mFaqList.get(getAdapterPosition()));
                } else {
                    Toast.makeText(v.getContext(), "True = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    Log.i(TAG,"upvote true");
                    mFaqItemListener.onDownvoteClick(mFaqList.get(getAdapterPosition()));

                }
            }

        }

        boolean userUpvoted (Faq faq) {
            if (faq.getUsersVoted().contains("1001688")) {
                return true;
            } else {
                return false;
            }
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements  View.OnClickListener {

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
                mFaqItemListener.onRetryClick();
            }
        }
    }
}
