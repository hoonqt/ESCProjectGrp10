package com.example.esc_50005.UI.Course.FAQ.addFaq;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.esc_50005.R;

/**
 * Created by Otter on 3/4/2018.
 */

public class AddFaqDialog extends DialogFragment implements AddFaqDialogContract.View {

    AddFaqDialogContract.Presenter mPresenter;
    SharedPreferences userInformation;

    EditText tv_question;
    EditText tv_answer;
    Button btn_save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_edit_faq, container, false);
        (rootView.findViewById(R.id.edit_faq_imgbtn_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        tv_answer = rootView.findViewById(R.id.edit_faq_et_answer);
        tv_question = rootView.findViewById(R.id.edit_faq_et_question);
        btn_save = rootView.findViewById(R.id.edit_faq_btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addFaq(tv_question.getText().toString(),tv_answer.getText().toString());
                dismissDialog();
            }
        });

        mPresenter = new AddFaqDialogPresenter(this);
        userInformation = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
//        userType = userInformation.getString("UserType","");
        String userId = userInformation.getString(getString(R.string.user_id),"");
        String courseId = userInformation.getString(getString(R.string.course_id), "");
        mPresenter.setUserId(userId);
        mPresenter.setCourseId(courseId);

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        return dialog;
    }

    @Override
    public void setPresenter(AddFaqDialogContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    @Override
    public void onResume() {
        // Get existing layout params for the window
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        // Assign window properties to fill the parent
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        // Call super onResume after sizing
        super.onResume();
    }
}
