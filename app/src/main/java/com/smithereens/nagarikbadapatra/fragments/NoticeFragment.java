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
import com.smithereens.nagarikbadapatra.entities.Office;
import com.smithereens.nagarikbadapatra.utils.SharedPrefHelper;

import java.util.ArrayList;


public class NoticeFragment extends Fragment {


    public NoticeFragment() {
        // Required empty public constructor
    }

    public static NoticeFragment newInstance(String param1, String param2) {
        NoticeFragment fragment = new NoticeFragment();
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
        View rootview =  inflater.inflate(R.layout.fragment_notice, container, false);

        updateUI(rootview);

        return rootview;
    }

    private void updateUI(View rootview) {

    }
}
