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

       TextView courseName;
       TextView courseId;

        CoursesViewHolder(View v){

            super(v);
            courseName = (TextView) v.findViewById(R.id.courseName);
            courseId= v.findViewById(R.id.courseId);
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
            String[] fullCourseName=course.split("\\s+");
            StringBuilder builder= new StringBuilder();
            for(int i=1; i<fullCourseName.length;i++)
            {
                builder.append(fullCourseName[i]);
                builder.append(" ");
            }
            courseId.setText(fullCourseName[0]);
            courseName.setText(builder.toString());
        }

        @Override
        public void onClick(View v)
        {
            int clickedPosition=getAdapterPosition();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String courseFullName=mCoursesList.get(clickedPosition);
            String[] splitCourseFullName=courseFullName.split("\\s+");
            StringBuilder builder= new StringBuilder();
            for(int i=1; i<splitCourseFullName.length;i++)
            {
                builder.append(splitCourseFullName[i]);
                builder.append(" ");
            }
            editor.putString(context.getResources().getString(R.string.course_name),builder.toString());
            editor.putString(context.getResources().getString(R.string.course_full_name),mCoursesList.get(clickedPosition));
            String[] fullName=mCoursesList.get(clickedPosition).split("\\s+");
            editor.putString(context.getResources().getString(R.string.course_id),fullName[0]);
            editor.commit();
            Log.i("this is it",sharedPreferences.getString(context.getResources().getString(R.string.course_name),""));

            mCoursesItemListener.moveToCourseScreen(mCoursesList.get(getAdapterPosition()));
        }

    }
}
