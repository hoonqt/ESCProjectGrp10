package com.example.esc_50005.UI.Session.feedbackDialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.esc_50005.R;

/**
 * Created by tan_j on 5/3/2018.
 */

public class FeedbackDialog extends DialogFragment implements FeedbackDialogContract.View {

    private static final String TAG = "FeedbackDialog";

    FeedbackDialogContract.Presenter mPresenter;

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

        View view = inflater.inflate(R.layout.dialog_feedback, container, false);

        mSubmitButton = (Button) view.findViewById(R.id.btn_submit);
        mMessage = (EditText) view.findViewById(R.id.et_message);
        mRatingBar = (RatingBar) view.findViewById(R.id.rating_bar_feedback);
        mPresenter = new FeedbackDialogPresenter(this);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.addFeedback(mRatingBar.getRating(), "comment");
                mPresenter.addFeedback(mRatingBar.getRating(), mMessage.getText().toString());
            }
        });

        return view;
    }

    void onLaterClick() {
        mPresenter.onLaterClicked();
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    @Override
    public void setPresenter(FeedbackDialogContract.Presenter presenter) {
        mPresenter = presenter;
    }


}