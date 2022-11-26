package com.zam.myinternship;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.zam.myinternship.api.CountryInfoAPI;
import com.zam.myinternship.model.CountryInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryInfoActivity extends AppCompatActivity {

    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvInfo=findViewById(R.id.tv_info);

        showInfo();
    }

    private void showInfo() {

        String[] add=getIntent().getStringArrayExtra("ADDRESSES");

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://restcountries.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountryInfoAPI countryInfoAPI=retrofit.create(CountryInfoAPI.class);

        for (int i=0; i<add.length; i++) {
            tvInfo.setText(tvInfo.getText()+" "+ i +" ");
            Call<ArrayList<CountryInfo>> call=countryInfoAPI
                    .getRandomAddresses("https://restcountries.com/v2/name/"+add[i]+"?fields=name,capital,population");

            call.enqueue(new Callback<ArrayList<CountryInfo>>() {
                @Override
                public void onResponse(Call<ArrayList<CountryInfo>> call, Response<ArrayList<CountryInfo>> response) {
                    if (!response.isSuccessful()) tvInfo.setText(tvInfo.getText()+"\n"+getString(R.string.not_found));
                    else {
                        ArrayList<CountryInfo> ans=response.body();
                        for (int i=0; i<ans.size(); i++) {
                            if (ans.get(i)==null) tvInfo.setText(tvInfo.getText()+"Country Not Found");
                            else {

                                tvInfo.setText(tvInfo.getText()+"\n"+ans.get(i).getName()+" "+ans.get(i).getCapital()+" "+ans.get(i).getPopulation());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<CountryInfo>> call, Throwable t) {
                    tvInfo.setText(t.getMessage());
                }
            });

        }
    }
}