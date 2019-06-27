package com.smithereens.nagarikbadapatra.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smithereens.nagarikbadapatra.R;
import com.smithereens.nagarikbadapatra.entities.Information;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.w3c.dom.Text;

import java.util.List;

public class InformationAdapter extends  RecyclerView.Adapter<InformationAdapter.InformationViewholder> {

    private RecyclerView mRecyclerView;
    private List<Information> mInformationList;

    private static final int UNSELECTED = -1;
    private int selectedItem = UNSELECTED;

    public InformationAdapter(RecyclerView recyclerView, List<Information> informationList) {
        this.mRecyclerView = recyclerView;
        mInformationList = informationList;
    }

    @NonNull
    @Override
    public InformationViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_information, parent, false);
        return new InformationViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationViewholder holder, int position) {
        holder.bind(mInformationList.get(position));
    }

    @Override
    public int getItemCount() {
        return mInformationList.size();
    }

    public class InformationViewholder extends RecyclerView.ViewHolder implements  View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener{

        private ExpandableLayout expandableLayout;
        private TextView titleTextView;
        private TextView requiredDocs;
        private TextView estimatedtime;
        private TextView estimatedcost;
        private TextView referredDepartment;
        private TextView referredPerson;
        private TextView complaintOfficer;
        private TextView additionalRemarks;

        public InformationViewholder(@NonNull View itemView) {
            super(itemView);

            expandableLayout = itemView.findViewById(R.id.info_row_expandable_layout);
            expandableLayout.setInterpolator(new OvershootInterpolator());
            expandableLayout.setOnExpansionUpdateListener(this);
            titleTextView = itemView.findViewById(R.id.info_row_title);
            requiredDocs = itemView.findViewById(R.id.item_row_req_docs);
            estimatedcost = itemView.findViewById(R.id.item_row_cost);
            estimatedtime = itemView.findViewById(R.id.item_row_time);
            referredDepartment = itemView.findViewById(R.id.item_row_ref_dept);
            referredPerson = itemView.findViewById(R.id.item_row_ref_person);
            complaintOfficer = itemView.findViewById(R.id.item_row_complaint_officer);
            additionalRemarks = itemView.findViewById(R.id.item_row_remarks);
            titleTextView.setOnClickListener(this

//                    new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    InformationViewholder holder = (InformationViewholder) mRecyclerView.findViewHolderForAdapterPosition(selectedItem);
//                    if (holder != null) {
//                        holder.titleTextView.setSelected(false);
//                        holder.expandableLayout.collapse();
//                    }
//
//                    int position = getAdapterPosition();
//                    if (position == selectedItem) {
//                        selectedItem = UNSELECTED;
//                    } else {
//                        titleTextView.setSelected(true);
//                        expandableLayout.expand();
//                        selectedItem = position;
//                    }
//                }
//            }
            );
        }

        public void bind(Information information){
            int position = getAdapterPosition();
            boolean isSelected = position == selectedItem;

            titleTextView.setText(information.getDescription());
            titleTextView.setSelected(isSelected);
            requiredDocs.setText(information.getReqDocList());
            estimatedtime.setText(information.getTime() + information.getTimeUnit());
            estimatedcost.setText(information.getCost().toString());
            referredDepartment.setText(information.getReferDepartment());
            referredPerson.setText(information.getReferPerson());
            complaintOfficer.setText(information.getComplaintOfficer());
            additionalRemarks.setText(information.getRemarks());
            expandableLayout.setExpanded(isSelected, false);
        }

        @Override
        public void onClick(View view) {
            InformationViewholder holder = (InformationViewholder) mRecyclerView.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                holder.titleTextView.setSelected(false);
                holder.expandableLayout.collapse();
            }

            int position = getAdapterPosition();
            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                titleTextView.setSelected(true);
                expandableLayout.expand();
                selectedItem = position;
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            Log.d("ExpandableLayout", "State: " + state);
            if (state == ExpandableLayout.State.EXPANDING) {
                mRecyclerView.smoothScrollToPosition(getAdapterPosition());
            }
        }
    }
}
