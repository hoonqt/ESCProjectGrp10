package com.example.esc_50005.UI.Login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.esc_50005.Database.utilities.Injection;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Dashboard.main.DashboardActivity;
import com.example.esc_50005.UI.SignUp.SignupActivity;

import static com.google.common.base.Preconditions.*;



/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {


    // UI references.
    private EditText mUserIdView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    public static String userId;
    public static String fullName;
    public static String password;
    public static String userType;
    Button signUp;
    SharedPreferences sharedPreferences;
    private LoginContract.Presenter mPresenter;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mPresenter = new LoginPresenter(
                Injection.provideUsersInformationRepository(getActivity().getApplicationContext()), this);
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
        signUp=view.findViewById(R.id.sign_up_button);
        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mPresenter.loadSignUp();

            }
        });
        mUserIdView = (EditText) view.findViewById(R.id.user_id);
        mPasswordView = (EditText) view.findViewById(R.id.password);
        RadioGroup selectProfessorOrStudentButton = (RadioGroup) view.findViewById(R.id.professorOrStudent);

        Button mSignInButton = (Button) view.findViewById(R.id.login_button);
        mSignInButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                userId=mUserIdView.getText().toString();
                Log.i("here at login",userId);
                password=mPasswordView.getText().toString();
                if(userType==null)
                {
                    userType="professor";
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.user_id), userId);
                editor.putString(getString(R.string.password), password);
                editor.putString(getString(R.string.user_type), userType);
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
                        Log.i("clicked","clicked professor");
                        userType="professor";
                        break;
                    case R.id.student:
                        Log.i("clicked","clickedstudent");
                        userType="student";
                        break;

                }
            }
        });
    }

    public void attemptLogin()
    {
        mPresenter.loadUsersFromDatabase(
                sharedPreferences.getString(getString(R.string.user_id),""),
                sharedPreferences.getString(getString(R.string.user_type),""),
                sharedPreferences.getString(getString(R.string.password),""));
    }

    public void showSuccessfulLogin(String userId) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(getString(R.string.user_id), userId);
//        editor.commit();
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showSecurityQuestion() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        builder.setTitle("You seem to have gotten one of your credentials wrong..c");
        LinearLayout layout = new LinearLayout(this.getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText question = new EditText(getActivity().getApplicationContext());
        question.setHint("What is your favorite food?");

        builder.setNegativeButton("Submit",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                mPresenter.verifySecurityAnswer(question.getText().toString(),userId,fullName);
                dialog.cancel();
            }
        });
        layout.addView(question);
        builder.setView(layout);
        builder.create();
        builder.show();
    }

    @Override
    public void showAccountLockedOut() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        builder.setTitle("Failed authentication");
        builder.create();
        builder.show();

    }

    @Override
    public void showSignUp() {

        Intent intent = new Intent(getActivity(), SignupActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

    public void showUnsuccessfulLogin()
    {
        mUserIdView.clearComposingText();
        mPasswordView.clearComposingText();
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage("Wrong username or password sorry! " );
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

        mPresenter.addBruteForceCount(userId,fullName);
    }

}

