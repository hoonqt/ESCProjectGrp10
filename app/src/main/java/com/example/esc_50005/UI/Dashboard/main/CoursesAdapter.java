package com.example.esc_50005.UI.Dashboard.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.esc_50005.R;

import java.util.ArrayList;


public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {

    public static final String TAG = "CoursesAdapter";

    private ArrayList<String> mCoursesList;
    private CoursesItemListener mCoursesItemListener;
    private Context context;
    private SharedPreferences sharedPreferences;
    private ImageButton deleteCourse;
    private DeleteCourseItemListener mDeleteCourseItemListener;

    private static int viewHolderCount = 0;


    public CoursesAdapter(ArrayList<String> coursesList, CoursesItemListener CoursesItemListener, Context context, DeleteCourseItemListener DeleteItemListener){
        Log.i("coursesAdapter","coursesAdapter");
        this.mDeleteCourseItemListener=DeleteItemListener;
        this.mCoursesList = coursesList;
        this.context=context;
        this.mCoursesItemListener=CoursesItemListener;
    }

    @Override
    public void onBindViewHolder(CoursesViewHolder holder, int position) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        Log.i("coursesSize",Integer.toString(mCoursesList.size()));
        if (mCoursesList == null) {
            return 0;
        } else {
            return mCoursesList.size();
        }
    }

    @Override
    public CoursesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.dashboard_courses_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        //java object of layout
        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        CoursesViewHolder coursesViewHolder = new CoursesViewHolder(view);
        viewHolderCount++;

        return coursesViewHolder;
    }


    class CoursesViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

       TextView question;

        CoursesViewHolder(View v){

            super(v);
            question = (TextView) v.findViewById(R.id.courseName);
            v.setOnClickListener(this);
            deleteCourse=v.findViewById(R.id.delete_course);
            deleteCourse.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int clickedPosition=getAdapterPosition();
                    String[] fullName=mCoursesList.get(clickedPosition).split("\\s+");
                    mDeleteCourseItemListener.deleteCourse(fullName[0],fullName[1]);
                }
            });

        }

        public void bind(int position ){
            String course = mCoursesList.get(position);
            question.setText(course);
        }

        @Override
        public void onClick(View v)
        {
            int clickedPosition=getAdapterPosition();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(context.getResources().getString(R.string.course_full_name),mCoursesList.get(clickedPosition));
            String[] fullName=mCoursesList.get(clickedPosition).split("\\s+");
            editor.putString(context.getResources().getString(R.string.course_id),fullName[0]);
            editor.commit();

            mCoursesItemListener.moveToCourseScreen(mCoursesList.get(getAdapterPosition()));
        }

    }
}
