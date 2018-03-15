package com.example.cindy.esc_50005.UI.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cindy.esc_50005.MainActivity;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqContract;

import static com.google.common.base.Preconditions.*;



/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    public static String username;
    public static String password;
    public static String userType;
    private LoginContract.Presenter mPresenter= new LoginPresenter(this);

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        Log.i("checkIfNull","checkIfNull");
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.login_fragment, container, false);
        setupLogin(view);

        return view;
    }


    public void setupLogin(final View view)
    {
        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.email);

        mPasswordView = (EditText) view.findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    password=mPasswordView.toString();
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) view.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                username=mEmailView.toString();
//                attemptLogin(view);
                attemptLogin();
            }
        });

        mLoginFormView = view.findViewById(R.id.login_form);
//        mProgressView = view.findViewById(R.id.login_progress);
        RadioGroup selectProfessorOrStudentButton = (RadioGroup) view.findViewById(R.id.professorOrStudent);

        selectProfessorOrStudentButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch(checkedId)
                {
                    case R.id.professor:
                        userType="professor";
                        break;
                    case R.id.student:
                        userType="student";
                        break;

                }
            }
        });
    }

    public boolean attemptLogin()
    {
        boolean result=mPresenter.checkIfLoginIsValid(username,password,userType);
        return result;
    }


    @Override
    public void setupLogin(LoginContract.View view) {

    }
}





