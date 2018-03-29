package com.example.esc_50005.UI.Session.feedback;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esc_50005.Database.feedback.Feedback;
import com.example.esc_50005.R;

import java.util.ArrayList;

/**
 * Created by Otter on 22/3/2018.
 */

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {

    public static final String TAG = "FeedbackAdapter";

    private ArrayList<Feedback> mFeedbackList;

    private static int viewHolderCount = 0;

    public FeedbackAdapter(ArrayList<Feedback> feedbackList){
        this.mFeedbackList = feedbackList;
    }


    public void onBindViewHolder(FeedbackViewHolder holder, int position) {
        holder.bind(position);
    }

    //indicates how many list objects it has

    public int getItemCount() {
        if (mFeedbackList == null) {
            return 0;
        } else {
            return mFeedbackList.size();
        }
    }

    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.item_feedback;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        //java object of layout
        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
        FeedbackViewHolder feedbackViewHolder = new FeedbackViewHolder(view);
        viewHolderCount++;

        return feedbackViewHolder;
    }

    class FeedbackViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView tv_author;
        TextView tv_comment;
        RatingBar rb_rating;

        FeedbackViewHolder(View v) {

            super(v);
            //we need the v object because the view contains the references to the widgets that we need
            tv_author = (TextView) v.findViewById(R.id.feedback_tv_author);
            tv_comment = (TextView) v.findViewById(R.id.feedback_tv_comment);
            rb_rating = (RatingBar) v.findViewById(R.id.feedback_rb_rating);

            v.setOnClickListener(this);

        }

        public void bind(int position) {

            Feedback feedback = mFeedbackList.get(position);
            tv_author.setText(feedback.getAuthor());
            tv_comment.setText(feedback.getComment());
            rb_rating.setRating(feedback.getRating());
        }

        @Override
        public void onClick(View v) {

                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();

        }
    }
}