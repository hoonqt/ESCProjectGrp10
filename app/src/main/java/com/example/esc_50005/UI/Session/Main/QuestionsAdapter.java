package com.example.esc_50005.UI.Session.Main;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.esc_50005.R;

import java.util.ArrayList;


public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    public static final String TAG = "QuestionsAdapter";

    private ArrayList<SessionQuestionsDO> mQuestionsList;
    private QuestionsItemListener mQuestionsItemListener;

    private static int viewHolderCount = 0;

    private String mUserId;

    public QuestionsAdapter(ArrayList<SessionQuestionsDO> questionsList, QuestionsItemListener itemListener, String userId) {
        this.mQuestionsList = questionsList;
        this.mQuestionsItemListener = itemListener;
        this.mUserId = userId;
    }

    @Override
    public void onBindViewHolder(QuestionsAdapter.QuestionsViewHolder holder, int position) {
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
        View view = inflater.inflate(layoutIDForListItem, parent, shouldAttachToParentImmediately);

        QuestionsViewHolder questionsViewHolder = new QuestionsViewHolder(view);
        viewHolderCount++;

        return questionsViewHolder;
    }


    class QuestionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            SessionQuestionsDO question = mQuestionsList.get(position);
            tv_question.setText(question.getQuestion());
            tv_upvote.setText(String.valueOf(question.getUpvotes()));
//            tv_time.setText(question.getDate());

//            tv_question.setText(questionsList.getQuestion());
//            tv_upvote.setText(String.valueOf(faq.getUpvotes()));
//            tv_time.setText(faq.getAuthor() + ", " + faq.getDate());
            upvoted = userUpvoted(question);
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
                    Log.i(TAG, "upvote false");
                    mQuestionsItemListener.onUpvoteClick(mQuestionsList.get(getAdapterPosition()));
                } else {
                    Toast.makeText(v.getContext(), "True = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "upvote true");
                    mQuestionsItemListener.onDownvoteClick(mQuestionsList.get(getAdapterPosition()));

                }
            }

//            if (v.getId() == btn_upvote.getId()) {
//                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//                mQuestionsItemListener.onUpvoteClick(mQuestionsList.get(getAdapterPosition()));
//            }

        }

        boolean userUpvoted(SessionQuestionsDO question) {
            if (question.getUsersVoters().contains(mUserId)) {
                return true;
            } else {
                return false;
//            }
            }
        }
    }
}
