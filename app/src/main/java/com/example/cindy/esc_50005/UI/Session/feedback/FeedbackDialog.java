package com.example.cindy.esc_50005.UI.Session.feedback;

import android.app.DialogFragment;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.cindy.esc_50005.R;

/**
 * Created by tan_j on 5/3/2018.
 */

public class FeedbackDialog extends DialogFragment implements FeedbackContract.View {

    private static final String TAG = "FeedbackDialog";

    FeedbackContract.Presenter mPresenter;

    RatingBar mRatingBar;
    EditText mMessage;
    View mRatingMessageView;
    Button mSubmitButton;

    public static FeedbackDialog newInstance() {
        FeedbackDialog fragment = new FeedbackDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public FeedbackDialog() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_rating, container, false);

        mSubmitButton = (Button) view.findViewById(R.id.btn_submit);
        mRatingMessageView = view.findViewById(R.id.view_rating_message);
        mMessage = (EditText) view.findViewById(R.id.et_message);
        mRatingBar = (RatingBar) view.findViewById(R.id.rating_bar_feedback);
        mPresenter = new FeedbackPresenter(this);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onRatingSubmitted(mRatingBar.getRating(), "");
//                mPresenter.onRatingSubmitted(mRatingBar.getRating(), mMessage.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void showRatingMessageView() {
        mRatingMessageView.setVisibility(View.VISIBLE);
    }

    void onLaterClick() {
        mPresenter.onLaterClicked();
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    @Override
    public void hideSubmitButton() {
        mSubmitButton.setVisibility(View.GONE);
    }

    @Override
    public void showFeedbackSubmitted() {
        Toast.makeText(getActivity().getApplicationContext(),"feedback is submitted",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(FeedbackContract.Presenter presenter) {
        mPresenter = presenter;
    }


}