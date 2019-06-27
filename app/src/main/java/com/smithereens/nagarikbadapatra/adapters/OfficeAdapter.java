package com.smithereens.nagarikbadapatra.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.smithereens.nagarikbadapatra.MainActivity;
import com.smithereens.nagarikbadapatra.R;
import com.smithereens.nagarikbadapatra.entities.Office;
import com.smithereens.nagarikbadapatra.fragments.InformationFragment;

import java.util.List;

public class OfficeAdapter  extends RecyclerView.Adapter<OfficeAdapter.OfficeViewHolder> {

    private Context mContext;
    private List<Office> officeList;
    public OfficeAdapter(Context context, List<Office> officeList){
        this.mContext = context;
        this.officeList = officeList;
    }

    @NonNull
    @Override
    public OfficeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_office, parent, false);
        return new OfficeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfficeViewHolder holder, final int position) {
        holder.bind(officeList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("officeid", String.valueOf(officeList.get(position).getId()));
                InformationFragment myObj = new InformationFragment();
                myObj.setArguments(bundle);
                FragmentTransaction fragmentTransaction = ((MainActivity)mContext).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, myObj);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return officeList == null ? 0 : officeList.size();
    }

    public class OfficeViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subTitle;

        public OfficeViewHolder(@NonNull View itemView) {

            super(itemView);

            title = itemView.findViewById(R.id.cardview_title);
            subTitle = itemView.findViewById(R.id.cardview_text);
        }

        public void bind(Office office){
            title.setText(office.getHeader3());
            subTitle.setText(office.getHeader4());
        }
    }
}
