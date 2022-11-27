package com.zam.myinternship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.zam.myinternship.adapter.CountryInfoAdapter;
import com.zam.myinternship.api.CountryInfoAPI;
import com.zam.myinternship.model.CountryInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //getting references to UI views
        toolbar=findViewById(R.id.toolbar);
        rvInfo=findViewById(R.id.rv_info);

        //setting toolbar and enabling going back to parent activity
        toolbar.setTitle(R.string.show_info);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //giving layout manager to recyclerview
        rvInfo.setLayoutManager(new LinearLayoutManager(this));

        getInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //finish activity when 'up' button in toolbar clicked
        finish();
        return true;
    }

    private void getInfo() {
        String[] add=getIntent().getStringArrayExtra("ADDRESSES");
        ArrayList<CountryInfo> countriesInfo= new ArrayList<>();
        CountryInfoAdapter countryInfoAdapter=new CountryInfoAdapter(this,countriesInfo);

        /*instantiating the retrofit and passing all the necessary arguments and requesting required amount of data in the body of for loop
        and getting response inside body of for loop in call.enqueue method */
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://restcountries.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountryInfoAPI countryInfoAPI=retrofit.create(CountryInfoAPI.class);

        for (int i=0; i<add.length; i++) {
            Call<ArrayList<CountryInfo>> call=countryInfoAPI
                    .getCountryInfo("https://restcountries.com/v2/name/"+add[i]+"?fields=name,capital,population,languages");

            int finalI = i;
            call.enqueue(new Callback<ArrayList<CountryInfo>>() {
                @Override
                public void onResponse(Call<ArrayList<CountryInfo>> call, Response<ArrayList<CountryInfo>> response) {
                    //if response is successful get response and update adapter of the recyclerview or update with 'default' values
                    if (response.isSuccessful()) countryInfoAdapter.updateAdapter(response.body().get(0));
                    else {
                        String sNotFound=getString(R.string.not_found);
                        countryInfoAdapter.updateAdapter(new CountryInfo(add[finalI]
                                ,sNotFound, 0L, null)
                        );
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<CountryInfo>> call, Throwable t) {
                    Toast.makeText(CountryInfoActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        rvInfo.setAdapter(countryInfoAdapter);
    }
}