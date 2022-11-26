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
        if (countriesInfo.get(position)!=null) {
            CountryInfo countryInfo=countriesInfo.get(position);
            holder.tvCountryName.setText(context.getString(R.string.country)+countryInfo.getName());
            holder.tvCountryCapital.setText(context.getString(R.string.capital)+countryInfo.getCapital());
            holder.tvCountryPopulation.setText(context.getString(R.string.population)+countryInfo.getPopulation().toString());
            holder.tvCountryLanguages.setText(
                    context.getString(R.string.languages)
                            +context.getString(R.string.iso_1)+countryInfo.getLanguage().get(0).getIso6391()
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
        countriesInfo.add(countryInfo);
        notifyDataSetChanged();
    }

    public class InfoHolder extends RecyclerView.ViewHolder{

        TextView tvCountryName, tvCountryCapital, tvCountryPopulation, tvCountryLanguages;

        public InfoHolder(@NonNull View itemView) {
            super(itemView);
            tvCountryName=itemView.findViewById(R.id.tv_country_name);
            tvCountryCapital=itemView.findViewById(R.id.tv_country_capital);
            tvCountryPopulation=itemView.findViewById(R.id.tv_country_population);
            tvCountryLanguages=itemView.findViewById(R.id.tv_country_languages);
        }
    }
}
