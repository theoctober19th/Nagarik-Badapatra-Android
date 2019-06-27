package com.smithereens.nagarikbadapatra.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smithereens.nagarikbadapatra.R;
import com.smithereens.nagarikbadapatra.adapters.HistoryAdapter;
import com.smithereens.nagarikbadapatra.custom.TinyDB;
import com.smithereens.nagarikbadapatra.entities.Office;
import com.smithereens.nagarikbadapatra.utils.SharedPrefHelper;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    RecyclerView mRecyclerView;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_history, container, false);

        updateUI(rootview);

        return rootview;
    }

    private void updateUI(View rootview) {

        mRecyclerView = rootview.findViewById(R.id.history_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Office> historyList = SharedPrefHelper.getArrayListOffice("history", getContext());
        if(historyList == null){
            historyList = new ArrayList<Office>();
        }

        HistoryAdapter historyAdapter = new HistoryAdapter(R.layout.item_history, historyList, getContext());

        mRecyclerView.setAdapter(historyAdapter);
    }
}
