package com.zam.myinternship.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zam.myinternship.R;
import com.zam.myinternship.model.CountryInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CountryInfoAdapter extends RecyclerView.Adapter<CountryInfoAdapter.InfoHolder> {

    private Context context;
    private ArrayList<CountryInfo> countriesInfo;

    public CountryInfoAdapter(Context context, ArrayList<CountryInfo> countriesInfo) {
        this.context=context;
        this.countriesInfo = countriesInfo;
    }

    @NonNull
    @Override
    public CountryInfoAdapter.InfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_holder,parent,false);
        return new CountryInfoAdapter.InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryInfoAdapter.InfoHolder holder, int position) {
        CountryInfo countryInfo=countriesInfo.get(position);

        holder.tvPosition.setText(String.valueOf(position+1));

        holder.tvCountryName.setText(countryInfo.getName());

        if (countryInfo.getCapital()==null)  holder.tvCountryCapital.setText(context.getString(R.string.not_found));
        else  holder.tvCountryCapital.setText(countryInfo.getCapital());

        if (countryInfo.getPopulation()==0) holder.tvCountryPopulation.setText(context.getString(R.string.not_found));
        else holder.tvCountryPopulation.setText(countryInfo.getPopulation().toString());

        if (countryInfo.getLanguage()==null) {
            holder.tvCountryLanguages.setText(context.getString(R.string.not_found));
        }
        else {
            holder.tvCountryLanguages.setText(context.getString(R.string.iso_1)+countryInfo.getLanguage().get(0).getIso6391()
                            +context.getString(R.string.iso_2)+countryInfo.getLanguage().get(0).getIso6392()
                            +context.getString(R.string.name)+countryInfo.getLanguage().get(0).getName()
                            +context.getString(R.string.native_name)+countryInfo.getLanguage().get(0).getNativeName()
            );
        }
    }

    @Override
    public int getItemCount() {
        return countriesInfo.size();
    }

    public void updateAdapter(CountryInfo countryInfo) {
        //get new countryInfo and add to 'countriesInfo' and sort in descending order
        countriesInfo.add(countryInfo);
        Collections.sort(countriesInfo, new Comparator<CountryInfo>() {
            @Override
            public int compare(CountryInfo o1, CountryInfo o2) {
                // '*-1' to be able to sort in descending order
                return o1.getPopulation().compareTo(o2.getPopulation())*-1;
            }
        });
        notifyDataSetChanged();
    }

    public class InfoHolder extends RecyclerView.ViewHolder{

        TextView tvPosition, tvCountryName, tvCountryCapital, tvCountryPopulation, tvCountryLanguages;

        public InfoHolder(@NonNull View itemView) {
            super(itemView);
            tvPosition=itemView.findViewById(R.id.tv_position);
            tvCountryName=itemView.findViewById(R.id.tv_country_name);
            tvCountryCapital=itemView.findViewById(R.id.tv_country_capital);
            tvCountryPopulation=itemView.findViewById(R.id.tv_country_population);
            tvCountryLanguages=itemView.findViewById(R.id.tv_country_languages);
        }
    }
}
