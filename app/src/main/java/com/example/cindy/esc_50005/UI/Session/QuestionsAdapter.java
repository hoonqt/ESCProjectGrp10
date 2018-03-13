package com.example.cindy.esc_50005.UI.Session;

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

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.R;

import java.util.ArrayList;


public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    public static final String TAG = "QuestionsAdapter";

    private ArrayList<SessionQuestionsDO> mQuestionsList;
    private QuestionsItemListener mQuestionsItemListener;

    private static int viewHolderCount = 0;


    public QuestionsAdapter(ArrayList<SessionQuestionsDO> questionsList, QuestionsItemListener itemListener){
        this.mQuestionsList = questionsList;
        this.mQuestionsItemListener = itemListener;
    }

    @Override
    public void onBindViewHolder(com.example.cindy.esc_50005.UI.Session.QuestionsAdapter.QuestionsViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        if (mQuestionsList == null) {
            return 0;
        } else {
            return mQuestionsList.size();
        }
    }

    @Override
    public QuestionsAdapter.QuestionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.sessionquestions_postquestions_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        //java object of layout
        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        QuestionsViewHolder questionsViewHolder = new QuestionsViewHolder(view);
        viewHolderCount++;

        return questionsViewHolder;
    }


    class QuestionsViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

       TextView question;

        QuestionsViewHolder(View v){

            super(v);

            question = (TextView) v.findViewById(R.id.item_post_question);
//            btn_upvote = (Button) v.findViewById(R.id.faq_btn_upvote);


            v.setOnClickListener(this);
//            btn_upvote.setOnClickListener(this);

        }

        public void bind(int position ){
            SessionQuestionsDO questionsList = mQuestionsList.get(position);
            question.setText(questionsList.getQuestion());
//            tv_upvote.setText(String.valueOf(faq.getUpvotes()));
//            tv_time.setText(faq.getAuthor() + ", " + faq.getDate());
        }

        @Override
        public void onClick(View v)
        {
//            if (v.getId() == btn_upvote.getId()) {
//                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//                mQuestionsItemListener.onUpvoteClick(mQuestionsList.get(getAdapterPosition()));
//            }

        }



    }
}
