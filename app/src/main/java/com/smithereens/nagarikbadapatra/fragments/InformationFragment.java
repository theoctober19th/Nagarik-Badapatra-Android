package com.smithereens.nagarikbadapatra.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smithereens.nagarikbadapatra.MainActivity;
import com.smithereens.nagarikbadapatra.R;
import com.smithereens.nagarikbadapatra.adapters.InformationAdapter;
import com.smithereens.nagarikbadapatra.api.ApiDataService;
import com.smithereens.nagarikbadapatra.api.RetrofitClientInstance;
import com.smithereens.nagarikbadapatra.custom.TinyDB;
import com.smithereens.nagarikbadapatra.entities.Information;
import com.smithereens.nagarikbadapatra.entities.Office;
import com.smithereens.nagarikbadapatra.map.MapsActivity;
import com.smithereens.nagarikbadapatra.utils.SharedPrefHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InformationFragment extends Fragment {

    private static final String OFFICE_ID = "1";
    RecyclerView mInformationRecylerView;
    ProgressBar mProgressBar;
    Bundle mBundle;
    private String OFFICEID = "0";

    public InformationFragment() {
        // Required empty public constructor
    }

    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
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
        final View rootview = inflater.inflate(R.layout.fragment_information, container, false);
        mProgressBar = rootview.findViewById(R.id.information_progressbar);
        mBundle  = this.getArguments();
        if (mBundle != null) {
            OFFICEID = mBundle.getString("officeid", "0");
        }

        final String ipaddress = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("ipaddress", "192.168.1.100");

        RetrofitClientInstance.getRetrofitInstance(ipaddress).create(ApiDataService.class).getAllInfoOfOffice(OFFICEID)
        .enqueue(new Callback<List<Information>>() {
            @Override
            public void onResponse(Call<List<Information>> call, Response<List<Information>> response) {
                final List<Information> list = response.body();
                if(list==null || list.size() == 0){
                    showAlertDialog("Error " + OFFICEID);
                }else{
                    RetrofitClientInstance.getRetrofitInstance(ipaddress).create(ApiDataService.class).getOfficeDetail(OFFICEID)
                            .enqueue(new Callback<Office>() {
                                @Override
                                public void onResponse(Call<Office> call, Response<Office> response) {
                                    Office office = response.body();
                                    if(office==null){
                                        showAlertDialog("Error");
                                    }else{
                                        updateUIViews(rootview, list, office);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Office> call, Throwable t) {
                                    Log.e("MYAPI", t.toString());
                                }
                            });
                }

            }

            @Override
            public void onFailure(Call<List<Information>> call, Throwable t) {
                showAlertDialog("Error");
                Log.d("MYAPI", t.toString());
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        return rootview;
    }

    private void updateUIViews(View rootview, List<Information> informationList, Office office) {

        mInformationRecylerView = rootview.findViewById(R.id.information_recyclerview);
        mInformationRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mInformationRecylerView.setAdapter(new InformationAdapter(mInformationRecylerView, informationList));
        
        TextView mapLinkTextView = rootview.findViewById(R.id.map_link);
        mapLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });

        TextView infoheader1 = rootview.findViewById(R.id.info_header1);
        infoheader1.setText(office.getHeader1());
        TextView infoheader2 = rootview.findViewById(R.id.info_header2);
        infoheader2.setText(office.getHeader2());
        TextView infoheader3 = rootview.findViewById(R.id.info_header3);
        infoheader3.setText(office.getHeader3());
        TextView infoheader4 = rootview.findViewById(R.id.info_header4);
        infoheader4.setText(office.getHeader4());
        TextView contactText = rootview.findViewById(R.id.contact_text);
        contactText.setText(office.getContact()+ "\n" + office.getHeadPersonnelContact() + "\nसूचना अधिकृत : " + office.getInformationOfficer() + "\n" + office.getInformationDeskContact());

        ArrayList<Office> historyList = SharedPrefHelper.getArrayListOffice("history", getContext());
        if(historyList == null){
            historyList = new ArrayList<Office>();
        }
        if(!historyList.contains(office)){
            historyList.add(office);
        }
        SharedPrefHelper.saveArrayListOffice(historyList, "history", getContext());

        if(mProgressBar.getVisibility() == ProgressBar.VISIBLE)
        {
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        }
    }

    private void openMap() {
        Intent intent = new Intent(getContext(), MapsActivity.class);
        startActivity(intent);
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
