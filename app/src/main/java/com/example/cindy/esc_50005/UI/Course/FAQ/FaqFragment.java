package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class FaqFragment extends Fragment implements FaqContract.FaqContractView {

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private FaqContract.FaqContractPresenter mPresenter;
    private  @NonNull
    FaqContract.FaqContractPresenter presenter;

    FaqJsonData[] faqJsonData;
    private FaqAdapter mFaqAdapter;

    ArrayList<FaqJsonData> FaqList;

    public FaqFragment() {
        // Required empty public constructor
    }
    @Override
    public void setPresenter() {
        Log.i("checkIfNull","checkIfNull");

        mPresenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parseJson();
        View view=inflater.inflate(R.layout.faq_main, container, false);
        showFaq(view);
        return view;
    }

    public void showFaq(View view)
    {
        RecyclerView faqListRecycler=(RecyclerView) view.findViewById(R.id.recyclerViewFaqs);
        mLayoutManager= new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        faqListRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mFaqAdapter=new FaqAdapter(getContext(),faqJsonData);
        faqListRecycler.setAdapter(mFaqAdapter);

    }

    public void showNoFaq()
    {

    }
    public void showLoadFaqError()
    {

    }


    public void AddItemsToRecyclerViewArrayList(){

        FaqList = new ArrayList<>();
    }

    public class FaqJsonData {

        String question;
        String answer;
        String upvotes;

    }
    private String readTxt(int resource) {

        InputStream inputStream = getResources().openRawResource(resource);
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
        String myJsonData=readTxt(R.raw.faq);
        faqJsonData=gson.fromJson(myJsonData, FaqJsonData[].class);
    }

}
