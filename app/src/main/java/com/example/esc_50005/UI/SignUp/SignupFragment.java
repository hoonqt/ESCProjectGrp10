package com.example.esc_50005.UI.SignUp;

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
import com.example.esc_50005.UI.Dashboard.main.DashboardActivity;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements SignupContract.View {


    // UI references.
    private EditText mUserIdView;
    private EditText mPasswordView;
    private EditText mFullNameView;
    private EditText mSecurityAnswerView;
    public static String userId;
    public static String password;
    public static String userType;
    public static String securityAnswer;
    public static String fullName;
    SharedPreferences sharedPreferences;
    private SignupContract.Presenter mPresenter= new SignupPresenter(this);


    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void setPresenter(@NonNull SignupContract.Presenter presenter) {
        Log.i("checkIfNull","checkIfNull");
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view=inflater.inflate(R.layout.signup_fragment, container, false);
        setUpSignup(view);
        return view;
    }


    public void setUpSignup(View view)
    {
        mUserIdView = (EditText) view.findViewById(R.id.user_id);
        mPasswordView = (EditText) view.findViewById(R.id.password);
        mFullNameView = (EditText) view.findViewById(R.id.full_name);
        mSecurityAnswerView = (EditText) view.findViewById(R.id.security_answer);
        RadioGroup selectProfessorOrStudentButton = (RadioGroup) view.findViewById(R.id.professorOrStudent);

        Button mSignUpButton = (Button) view.findViewById(R.id.submit_signup);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                userId=mUserIdView.getText().toString();
                securityAnswer=mSecurityAnswerView.getText().toString();
                password=mPasswordView.getText().toString();
                fullName=mFullNameView.getText().toString();
                if(userType==null)
                {
                    userType="professor";
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.user_id), userId);
                editor.putString(getString(R.string.password), password);
                editor.putString(getString(R.string.user_type), userType);
                editor.putString(getString(R.string.full_name), fullName);
                editor.putString(getString(R.string.security_answer), securityAnswer);
                editor.commit();
                attemptSignup();
            }
        });


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

    public void attemptSignup()
    {
        String userId=sharedPreferences.getString(getString(R.string.user_id),"");
        String fullName=sharedPreferences.getString(getString(R.string.full_name),"");
        String password=sharedPreferences.getString(getString(R.string.password),"");
        String userType=sharedPreferences.getString(getString(R.string.user_type),"");
        String securityAnswer=sharedPreferences.getString(getString(R.string.security_answer),"");
        mPresenter.loadUsersFromDatabase(userId,fullName, password, userType, securityAnswer);
    }

    @Override
    public void showUnsuccessfulSignup() {
        mFullNameView.clearComposingText();
        mUserIdView.clearComposingText();
        mSecurityAnswerView.clearComposingText();
        mPasswordView.clearComposingText();
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage("User Id already exists! " );
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }

    @Override
    public void showSuccessfulSignup() {

        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}





