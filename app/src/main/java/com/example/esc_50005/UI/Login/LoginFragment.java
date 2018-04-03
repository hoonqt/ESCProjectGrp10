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
        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.email);
        mPasswordView = (EditText) view.findViewById(R.id.password);
        RadioGroup selectProfessorOrStudentButton = (RadioGroup) view.findViewById(R.id.professorOrStudent);

        Button mEmailSignInButton = (Button) view.findViewById(R.id.login_button);
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
                        Log.i("clicked","clickedstudent");
                        userType="student";
                        break;

                }
            }
        });
    }

    public void attemptLogin()
    {
        mPresenter.loadUsersFromDatabase(sharedPreferences.getString("Username",""),sharedPreferences.getString("UserType",""),sharedPreferences.getString("Password",""));
    }

    public void showSuccessfulLogin(Double userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserId", Integer.toString(userId.intValue()));
        editor.commit();
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showSecurityQuestion() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        builder.setTitle("You seem to have forgotten your password..");
        LinearLayout layout = new LinearLayout(this.getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText question = new EditText(getActivity().getApplicationContext());
        question.setHint("What is your favorite color?");

        builder.setNegativeButton("Submit",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                mPresenter.verifySecurityAnswer(question.getText().toString(),userType,username);
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
        builder.setTitle("Your account has been locked out, kindly contact your adminstrator.");
        builder.create();
        builder.show();

    }

    public void showUnsuccessfulLogin()
    {
        mEmailView.clearListSelection();
        mPasswordView.clearComposingText();
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage("Wrong username or password sorry! " );
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

        mPresenter.addBruteForceCount(username,userType);
    }

}





