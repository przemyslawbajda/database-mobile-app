package com.example.lab2_database_mobile_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {
    private List<Phone> phoneList;
    private LayoutInflater layoutInflater;

    public PhoneListAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        this.phoneList = null;

    }

    @NonNull
    @Override
    public PhoneListAdapter.PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout to viewholder
        View row = layoutInflater.inflate(R.layout.row_layout, parent, false);

        return new PhoneViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneListAdapter.PhoneViewHolder holder, int position) {
        //invoked when new row is going to be displayed

        Phone phoneElement = phoneList.get(position);
        holder.manufacturerText.setText(phoneElement.getManufacturer());
        holder.modelText.setText(phoneElement.getModel());

    }

    @Override
    public int getItemCount() {
        if(phoneList != null){
            return phoneList.size();
        }
        return 0;
    }

    //allows updates of the data in the adapter what means also the data displayed in RecyclerView
    public void setPhoneList(List<Phone> phoneList){
        this.phoneList = phoneList;
        notifyDataSetChanged();
    }

    //store elements of single row
    public class PhoneViewHolder extends RecyclerView.ViewHolder{
        TextView manufacturerText;
        TextView modelText;

        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);

            manufacturerText = itemView.findViewById(R.id.manufacturerText);
            modelText = itemView.findViewById(R.id.modelText);
        }
    }
}
