package com.example.esc_50005.UI.Login;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.esc_50005.R;
import com.example.esc_50005.UI.Course.FAQ.CourseActivity;
import com.example.esc_50005.UI.Session.Main.SessionActivity;

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
    SharedPreferences sharedPreferences;
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
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view=inflater.inflate(R.layout.login_fragment, container, false);
        setUpLogin(view);
        return view;
    }


    public void setUpLogin(View view)
    {
        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.email);
        mPasswordView = (EditText) view.findViewById(R.id.password);
        RadioGroup selectProfessorOrStudentButton = (RadioGroup) view.findViewById(R.id.professorOrStudent);

        Button mEmailSignInButton = (Button) view.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                username=mEmailView.getText().toString();
                password=mPasswordView.getText().toString();
                if(userType==null)
                {
                    userType="professor";
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Username", username);
                editor.putString("Password", password);
                editor.putString("UserType", userType);
                Log.i("answer",sharedPreferences.getString("Username",""));
                editor.commit();
                attemptLogin();
            }
        });

        mLoginFormView = view.findViewById(R.id.login_form);

        selectProfessorOrStudentButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch(checkedId)
                {
                    case R.id.professor:
                        Log.i("clicked","clicked");
                        userType="professor";
                        break;
                    case R.id.student:
                        userType="student";
                        break;

                }
            }
        });
    }

    public void attemptLogin()
    {
        mPresenter.loadUsersFromDatabase(getActivity().getApplicationContext());
    }

    public void showSuccessfulLogin()
    {
        Log.i("showSuccessfulLogin","showSuccessfulLogin");
        Intent intent = new Intent(getActivity(), CourseActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void showUnsuccessfulLogin()
    {
        mEmailView.clearListSelection();
        mPasswordView.clearComposingText();
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage("Wrong username or password sorry! " );
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}





