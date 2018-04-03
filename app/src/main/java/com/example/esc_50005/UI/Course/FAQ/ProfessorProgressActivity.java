package com.example.esc_50005.UI.Course.FAQ;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.esc_50005.MainActivity;
import com.example.esc_50005.R;

public class ProfessorProgressActivity extends AppCompatActivity {
    private static final String TAG = "ProfProgAct";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String studentId;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                studentId= null;
            } else {
                studentId= extras.getString("STUDENT_ID");
            }
        } else {
            studentId= (String) savedInstanceState.getSerializable("STUDENT_ID");
        }
        Log.i(TAG, "STUDENT_ID: " + studentId);

        Intent intent = new Intent(getApplicationContext(), ProgressStudentFragment.class);
        intent.putExtra("STUDENT_ID", studentId);
        setContentView(R.layout.professor_progress_main);
        ProgressStudentFragment progressStudentFragment = new ProgressStudentFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_holder,progressStudentFragment);
        ft.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//addd back button later

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), CourseActivity.class);
                startActivity(intent);
            }
        });

    }

}


