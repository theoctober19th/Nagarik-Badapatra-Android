package com.smithereens.nagarikbadapatra.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.smithereens.nagarikbadapatra.MainActivity;
import com.smithereens.nagarikbadapatra.R;
import com.smithereens.nagarikbadapatra.adapters.OfficeAdapter;
import com.smithereens.nagarikbadapatra.api.ApiDataService;
import com.smithereens.nagarikbadapatra.api.RetrofitClientInstance;
import com.smithereens.nagarikbadapatra.entities.Information;
import com.smithereens.nagarikbadapatra.entities.Office;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    RecyclerView mFrequentRecyclerView;
    RecyclerView mNearbyRecyclerView;
    RecyclerView mPastRecyclerView;

    OfficeAdapter mOfficeAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView dateTextView = rootView.findViewById(R.id.datetext);
        dateTextView.setText("आज : 2076 - असार - 12 गते \n 2019 - June - 27\n समय : " + new SimpleDateFormat("hh:mm a").format(new Date()));
       showOfficesList(rootView);


        return rootView;
    }

    public void showOfficesList(final View rootView){
        String ipaddress = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("ipaddress", "192.168.1.100");

        RetrofitClientInstance.getRetrofitInstance(ipaddress).create(ApiDataService.class).getAllOffice()
                .enqueue(new Callback<List<Office>>() {
                    @Override
                    public void onResponse(Call<List<Office>> call, Response<List<Office>> response) {
                        if(response.body() != null){
                            List<Office> allofficelist = response.body();
                            mNearbyRecyclerView = rootView.findViewById(R.id.nearby_offices);
                            mNearbyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            mOfficeAdapter = new OfficeAdapter(getContext(), allofficelist);
                            mNearbyRecyclerView.setAdapter(mOfficeAdapter);


                            mFrequentRecyclerView = rootView.findViewById(R.id.frequent_offices);
                            mFrequentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            mFrequentRecyclerView.setAdapter(mOfficeAdapter);


                            mPastRecyclerView = rootView.findViewById(R.id.visited_offices);
                            mPastRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            mPastRecyclerView.setAdapter(mOfficeAdapter);
                        }else{

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Office>> call, Throwable t) {

                    }
                });



    }

    public void showAlertDialog(String hint){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle(hint);
        alertDialog.setMessage("Cannot fetch data from database.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                });
        alertDialog.show();
    }
}
