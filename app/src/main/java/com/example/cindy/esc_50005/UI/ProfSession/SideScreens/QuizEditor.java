package com.example.cindy.esc_50005.UI.ProfSession.SideScreens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.ProfSession.Adapters.ActivityProfAdapter;
import com.example.cindy.esc_50005.UI.ProfSession.Adapters.QuizEditorAdapter;
import com.example.cindy.esc_50005.UI.ProfSession.MainScreens.ActivityProfFrag;
import com.example.cindy.esc_50005.UI.ProfSession.ProfSessionActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizEditor extends Fragment {

    private RecyclerView quizRecycler;

    private QuizEditor.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private QuizEditorAdapter mQuizAdapter;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public QuizEditor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_quiz_editor, container, false);
        quizRecycler = view.findViewById(R.id.recyclerViewQuizAdder);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = QuizEditor.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        quizRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mQuizAdapter = new QuizEditorAdapter();
        quizRecycler.setAdapter(mQuizAdapter);

        ImageView adder = view.findViewById(R.id.addbutton);
        adder.setOnClickListener(btnListener);



        return view;
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {

        public void onClick(View v) {

            mQuizAdapter.incEntries();
            quizRecycler.setAdapter(mQuizAdapter);

        }

    };
}




