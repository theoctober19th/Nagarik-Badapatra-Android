package com.smithereens.nagarikbadapatra.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.smithereens.nagarikbadapatra.MainActivity;
import com.smithereens.nagarikbadapatra.R;
import com.smithereens.nagarikbadapatra.entities.Office;
import com.smithereens.nagarikbadapatra.fragments.InformationFragment;

import java.util.ArrayList;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private int listItemLayout;
    static private ArrayList<Office> itemList;static Context mContext;
    // Constructor of the class
    public HistoryAdapter(int layoutId, ArrayList<Office> itemList, Context context) {
        listItemLayout = layoutId;
        this.itemList = itemList;
        this.mContext = context;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
       item.setText(((Office)itemList.get(listPosition)).getHeader3() + ", " + itemList.get(listPosition).getHeader4());
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView item;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            item = (TextView) itemView.findViewById(R.id.row_item_history_textview);
        }

        public void onClick(View view) {

            int officeid = itemList.get(getAdapterPosition()).getId();

            System.out.println(officeid);

            Bundle bundle = new Bundle();
            bundle.putString("officeid", String.valueOf( officeid));
            InformationFragment myObj = new InformationFragment();
            myObj.setArguments(bundle);
            FragmentTransaction fragmentTransaction = ((MainActivity)mContext).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, myObj);
            fragmentTransaction.commit();
        }
    }
}