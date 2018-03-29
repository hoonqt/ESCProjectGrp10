package com.example.esc_50005.UI.Course.FAQ;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by cindy on 27/11/2017.
 */

public class FaqAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final String TAG = "FaqAdapter";

    private ArrayList<Faq> mFaqList;
    private FaqItemListener mFaqItemListener;

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private static int viewHolderCount = 0;

    public FaqAdapter(ArrayList<Faq> faqList, FaqItemListener itemListener){
        this.mFaqList = faqList;
        this.mFaqItemListener = itemListener;
    }


    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(position);
    }

    //indicates how many list objects it has

    public int getItemCount() {
        if (mFaqList == null) {
            return 0;
        } else {
            return mFaqList.size();
        }
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                view = inflater.inflate(R.layout.faq_recycler,parent,shouldAttachToParentImmediately);
                return new FaqViewHolder(view);
            case VIEW_TYPE_EMPTY:
                view = inflater.inflate(R.layout.item_empty_view,parent,shouldAttachToParentImmediately);
                return new EmptyViewHolder(view);
            default:
                view = inflater.inflate(R.layout.faq_recycler,parent,shouldAttachToParentImmediately);
                return new FaqViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mFaqList != null && mFaqList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    class FaqViewHolder extends BaseViewHolder implements  View.OnClickListener {

        TextView tv_question;
        TextView tv_answer;
        TextView tv_upvote;
        TextView tv_time;
        Button btn_upvote;
        boolean upvoted;

        FaqViewHolder(View v) {

            super(v);
            //we need the v object because the view contains the references to the widgets that we need
            tv_question = (TextView) v.findViewById(R.id.faq_tv_question);
            tv_answer = (TextView) v.findViewById(R.id.faq_tv_answer);
            tv_upvote = (TextView) v.findViewById(R.id.faq_tv_upvote);
            tv_time = (TextView) v.findViewById(R.id.faq_tv_time);
            btn_upvote = (Button) v.findViewById(R.id.faq_btn_upvote);

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