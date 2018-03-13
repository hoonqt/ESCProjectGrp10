package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.R;

import java.util.ArrayList;

/**
 * Created by cindy on 27/11/2017.
 */

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqViewHolder> {

    public static final String TAG = "FaqAdapter";

    private ArrayList<Faq> mFaqList;
    private FaqItemListener mFaqItemListener;

    private static int viewHolderCount = 0;

    public FaqAdapter(ArrayList<Faq> faqList, FaqItemListener itemListener){
        this.mFaqList = faqList;
        this.mFaqItemListener = itemListener;
    }


    public void onBindViewHolder(FaqViewHolder holder, int position) {
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

    public FaqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.faq_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        //java object of layout
        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
        FaqViewHolder faqViewHolder = new FaqViewHolder(view);
        viewHolderCount++;

        return faqViewHolder;
    }

    class FaqViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView tv_question;
        TextView tv_answer;
        TextView tv_upvote;
        TextView tv_time;
        Button btn_upvote;

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
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == btn_upvote.getId()) {
                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                mFaqItemListener.onUpvoteClick(mFaqList.get(getAdapterPosition()));
            }

        }
    }
}