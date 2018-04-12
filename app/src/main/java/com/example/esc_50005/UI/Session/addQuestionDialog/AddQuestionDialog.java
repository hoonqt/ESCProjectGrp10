package com.example.esc_50005.UI.Session.addQuestionDialog;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class AddQuestionDialog extends DialogFragment implements AddQuestionDialogContract.View {

    private static final String TAG = "AddQuestionDialog";

    AddQuestionDialogContract.Presenter mPresenter;

    EditText mMessage;
    Button mSubmitButton;
    Button mLaterButton;

    SharedPreferences userInformation;
    String sessionId;
    String userId;
    String name;

    public static AddQuestionDialog newInstance() {
        AddQuestionDialog fragment = new AddQuestionDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }

    public AddQuestionDialog() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_add_question, container, false);

        mLaterButton = (Button) view.findViewById(R.id.btn_later);
        mSubmitButton = (Button) view.findViewById(R.id.btn_submit);
        mMessage = (EditText) view.findViewById(R.id.et_message);
        mPresenter = new AddQuestionDialogPresenter(this);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.addFeedback(mRatingBar.getRating(), "comment");
                mPresenter.addQuestion(mMessage.getText().toString());
            }
        });

        mLaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });

        userInformation = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        sessionId = userInformation.getString(getString(R.string.session_id),"");
        userId = userInformation.getString(getString(R.string.user_id),"");
        name = userInformation.getString(getString(R.string.full_name),"");
        mPresenter.setSessionId(sessionId);
        mPresenter.setName(name);
        mPresenter.setUserId(userId);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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
    public void setPresenter(AddQuestionDialogContract.Presenter presenter) {
        mPresenter = presenter;
    }

}