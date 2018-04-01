package com.example.esc_50005.UI.Welcome;

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
import com.example.esc_50005.UI.Course.FAQ.FaqItemListener;
import com.example.esc_50005.UI.Dashboard.main.DashboardActivity;
import com.example.esc_50005.UI.Login.LoginActivity;
import com.example.esc_50005.UI.SignUp.SignupActivity;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment implements WelcomeContract.View {

    private View mLoginFormView;
    SharedPreferences sharedPreferences;
    private WelcomeContract.Presenter mPresenter= new WelcomePresenter(this);
    private Button signIn;
    private Button signUp;


    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void setPresenter(@NonNull WelcomeContract.Presenter presenter) {
        Log.i("checkIfNull","checkIfNull");
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view=inflater.inflate(R.layout.welcome_fragment, container, false);
        signIn=view.findViewById(R.id.sign_in_button);
        signUp=view.findViewById(R.id.sign_up_button);

        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mPresenter.loadSignUp();

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mPresenter.loadSignIn();

            }
        });

        return view;
    }


    @Override
    public void showSignUp() {

        Intent intent = new Intent(getActivity(), SignupActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

    @Override
    public void showSignIn() {

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

}





