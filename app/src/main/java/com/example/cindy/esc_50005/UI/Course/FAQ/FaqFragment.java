package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cindy.esc_50005.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * A simple {@link Fragment} subclass.
 */
public class FaqFragment extends Fragment implements FaqContract {

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;

    FaqJsonData[] faqJsonData;
    private FaqAdapter mFaqAdapter;

    public FaqFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parseJson();
        View view=inflater.inflate(R.layout.faq_main, container, false);

        RecyclerView faqList=(RecyclerView) view.findViewById(R.id.recyclerViewFaqs);
        mLayoutManager= new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        faqList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mFaqAdapter=new FaqAdapter(getContext(),faqJsonData);
        faqList.setAdapter(mFaqAdapter);
        return view;
    }

    public class FaqJsonData {

        String question;
        String answer;
        String upvotes;

    }
    private String readTxt(int resource) {

        InputStream inputStream = getResources().openRawResource(resource);
        //TODO 3.2 Complete readTxt to take in a resource ID of a file,
        //          read it and return it as a single string
        // Reads an InputStream and converts it to a String.

        String line;
        String output="";

        try{
            BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"));
            while( (line = reader.readLine()) != null){
                output = output + line;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return output;

    }

    void parseJson() {

        Gson gson = new Gson();
        //Gson is a Java library that can be used to convert Java Objects into their JSON representation.
        // It can also be used to convert a JSON string to an equivalent Java object.
        //TODO 3.3 Invoke readTxt
        String myJsonData=readTxt(R.raw.faq);
        Log.i("@@@@",myJsonData);
        //TODO 3.4 parse the JSON file
        faqJsonData=gson.fromJson(myJsonData, FaqJsonData[].class);
    }

}
