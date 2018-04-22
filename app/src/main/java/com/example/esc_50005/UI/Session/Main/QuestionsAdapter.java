package com.example.esc_50005.UI.Session.Main;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.esc_50005.Database.Database.Question;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Base.BaseViewHolder;

import java.util.ArrayList;


public class QuestionsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final String TAG = "QuestionsAdapter";

    private ArrayList<Question> mQuestionsList;
    private QuestionsItemListener mQuestionsItemListener;

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private static int viewHolderCount = 0;

    private String mUserId;

    public QuestionsAdapter(ArrayList<Question> questionsList, QuestionsItemListener itemListener, String userId) {
        this.mQuestionsList = questionsList;
        this.mQuestionsItemListener = itemListener;
        this.mUserId = userId;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (mQuestionsList != null && mQuestionsList.size()>0) {
            return mQuestionsList.size();
        } else {
            return 1;
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                view = inflater.inflate(R.layout.sessionquestions_postquestions_recycler,parent,shouldAttachToParentImmediately);
                return new QuestionsViewHolder(view);
            case VIEW_TYPE_EMPTY:
                view = inflater.inflate(R.layout.item_empty_view,parent,shouldAttachToParentImmediately);
                return new EmptyViewHolder(view);
            default:
                view = inflater.inflate(R.layout.sessionquestions_postquestions_recycler,parent,shouldAttachToParentImmediately);
                return new QuestionsViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mQuestionsList != null && mQuestionsList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    class QuestionsViewHolder extends BaseViewHolder implements View.OnClickListener {

        TextView tv_question;
        TextView tv_upvote;
//        TextView tv_time;
        Button btn_upvote;
        boolean upvoted;

        QuestionsViewHolder(View v) {

            super(v);

            tv_question = (TextView) v.findViewById(R.id.questions_tv_question);
            tv_upvote = (TextView) v.findViewById(R.id.questions_tv_upvotes);
            btn_upvote = (Button) v.findViewById(R.id.questions_btn_upvote);

            v.setOnClickListener(this);
            btn_upvote.setOnClickListener(this);
        }

        public void bind(int position) {
            Question question = mQuestionsList.get(position);
            tv_question.setText(question.getQuestion());
            tv_upvote.setText(String.valueOf(question.getUsersVoted().size())+" upvotes");
//            tv_time.setText(question.getDate());

//            tv_question.setText(questionsList.getQuestion());
//            tv_upvote.setText(String.valueOf(faq.getUpvotes()));
//            tv_time.setText(faq.getAuthor() + ", " + faq.getDate());
            upvoted = userUpvoted(question);
            if (upvoted) {
//                btn_upvote.setText("Downvote");
                btn_upvote.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
            } else {
//                btn_upvote.setText("Upvote");
                btn_upvote.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
            }
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == btn_upvote.getId()) {
                if (!upvoted) {
                    Log.i(TAG, "upvote false");
                    mQuestionsItemListener.onUpvoteClick(mQuestionsList.get(getAdapterPosition()));
                } else {
                    Log.i(TAG, "upvote true");
                    mQuestionsItemListener.onDownvoteClick(mQuestionsList.get(getAdapterPosition()));

                }
            }

//            if (v.getId() == btn_upvote.getId()) {
//                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//                mQuestionsItemListener.onUpvoteClick(mQuestionsList.get(getAdapterPosition()));
//            }

        }

        boolean userUpvoted(Question question) {
            if (question.getUsersVoted().contains(mUserId)) {
                return true;
            } else {
                return false;
//            }
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
                mQuestionsItemListener.onRetryClick();
            }
        }
    }
}
