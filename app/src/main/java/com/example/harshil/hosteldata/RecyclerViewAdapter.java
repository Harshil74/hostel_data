package com.example.harshil.hosteldata;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int MENU_ITEM_VIEW_TYPE = 0;
    private List<Details> mRecyclerViewItems;
    private Context mContext;

    public RecyclerViewAdapter(List<Details> mRecyclerViewItems, Context mContext) {
        this.mRecyclerViewItems = mRecyclerViewItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case MENU_ITEM_VIEW_TYPE :
            default:
                View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
                return new MenuItemViewHolder(menuItemLayoutView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case MENU_ITEM_VIEW_TYPE:
                default:
                    MenuItemViewHolder menuItemViewHolder= (MenuItemViewHolder) holder;
                    Details menuItem = (Details) mRecyclerViewItems.get(position);
                    menuItemViewHolder.menuRoomNo.setText(menuItem.getRoomNo());
                    menuItemViewHolder.menuEnrollNo.setText(menuItem.getEnrollNo());
                    menuItemViewHolder.menuFullName.setText(menuItem.getFullName());
                    menuItemViewHolder.menuBranch.setText(menuItem.getBranch());
                    menuItemViewHolder.menuYear.setText(menuItem.getYear());
                    menuItemViewHolder.menuEmail.setText(menuItem.getEmail());
                    menuItemViewHolder.menuContactNo.setText(menuItem.getContactNo());
                    menuItemViewHolder.menuEContactNo.setText(menuItem.geteContactNo());
                    menuItemViewHolder.menuAddres.setText(menuItem.getAddress());


        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }
    private class MenuItemViewHolder extends RecyclerView.ViewHolder{

        private TextView menuRoomNo,menuEnrollNo,menuFullName,menuBranch,menuYear,menuEmail,menuContactNo,menuEContactNo,menuAddres;
        public MenuItemViewHolder(View itemView) {
            super(itemView);
            menuRoomNo = itemView.findViewById(R.id.room_no);
            menuEnrollNo = itemView.findViewById(R.id.enroll_no);
            menuFullName = itemView.findViewById(R.id.std_name);
            menuBranch = itemView.findViewById(R.id.branch);
            menuYear = itemView.findViewById(R.id.year);
            menuEmail = itemView.findViewById(R.id.email);
            menuContactNo = itemView.findViewById(R.id.contact_no);
            menuEContactNo = itemView.findViewById(R.id.e_contact_no);
            menuAddres = itemView.findViewById(R.id.address);
        }
    }
    public void setFilter(List<Details> newlist){
        mRecyclerViewItems = new ArrayList<>();
        mRecyclerViewItems.addAll(newlist);
        notifyDataSetChanged();

    }
}
