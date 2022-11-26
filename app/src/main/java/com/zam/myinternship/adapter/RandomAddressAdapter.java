package com.zam.myinternship.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zam.myinternship.R;
import com.zam.myinternship.model.RandomAddress;

import java.util.ArrayList;

public class RandomAddressAdapter extends RecyclerView.Adapter<RandomAddressAdapter.AddressHolder> {

    private ArrayList<RandomAddress> randomAddresses;

    public RandomAddressAdapter(ArrayList<RandomAddress> randomAddresses) {
        this.randomAddresses = randomAddresses;
    }

    @NonNull
    @Override
    public RandomAddressAdapter.AddressHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_holder,parent,false);
        return new AddressHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomAddressAdapter.AddressHolder holder, int position) {
        RandomAddress randomAddress=randomAddresses.get(position);
        holder.tvCountry.setText((position+1)+" "+randomAddress.getCountry());
    }

    @Override
    public int getItemCount() {
        return randomAddresses.size();
    }

    public class AddressHolder extends RecyclerView.ViewHolder{

        TextView tvCountry;

        public AddressHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry=itemView.findViewById(R.id.tv_country);
        }
    }
}
