package com.zam.myinternship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.zam.myinternship.adapter.RandomAddressAdapter;
import com.zam.myinternship.api.RandomAddressAPI;
import com.zam.myinternship.model.RandomAddress;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomAddressActivity extends AppCompatActivity {

    public static final String BASE_URL="https://random-data-api.com/api/";

    private TextInputLayout tilNumber;
    private EditText etNumber;
    private Button bFetchData, bInfo;
    private RecyclerView rvMain;
    private int number=5;
    private ArrayList<RandomAddress> addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting references to UI views
        tilNumber=findViewById(R.id.til_number);
        etNumber=findViewById(R.id.et_number);
        bFetchData=findViewById(R.id.b_fetch_data);
        rvMain=findViewById(R.id.rv_main);
        bInfo=findViewById(R.id.b_info);

        //make a button to respond to clicks
        bFetchData.setOnClickListener(v -> {
            //check number range then get data from API
            if (checkNumberRange()) fetchData();
        });

        //giving layout manager to recyclerview
        rvMain.setLayoutManager(new LinearLayoutManager(this));

        //make a button to respond to clicks
        bInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiating the intent and passing all the countries generated from the random data API to the required activity to use
                Intent intent=new Intent(RandomAddressActivity.this, CountryInfoActivity.class);
                String[] ans=new String[addresses.size()];
                for (int i=0; i<addresses.size(); i++) ans[i]=addresses.get(i).getCountry();
                intent.putExtra("ADDRESSES", ans);
                startActivity(intent);
            }
        });
    }

    private boolean checkNumberRange() {
        try {
            //check and throw an exception if necessary
            number=Integer.parseInt(etNumber.getText().toString());
            if (number<5 || 20<number) throw new Exception("NumberOutOfRange");
            return true;
        } catch (Exception e) {
            //if exception caught enable error text in text input layout (tilNumber)
            tilNumber.setErrorEnabled(true);
            if (e instanceof NumberFormatException) tilNumber.setError(getString(R.string.number_null));
            else tilNumber.setError(getString(R.string.number_range));
            return false;
        }
    }

    private void fetchData() {

        //removing error after number validation
        tilNumber.setErrorEnabled(false);

        //instantiating the retrofit and passing all the necessary arguments and getting response in call.enqueue method
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RandomAddressAPI randomAddressAPI =retrofit.create(RandomAddressAPI.class);
        Call<ArrayList<RandomAddress>> call= randomAddressAPI.getRandomAddresses(BASE_URL+"address/random_address?size="+number);

        call.enqueue(new Callback<ArrayList<RandomAddress>>() {
            @Override
            public void onResponse(Call<ArrayList<RandomAddress>> call, Response<ArrayList<RandomAddress>> response) {
                addresses=response.body();

                //getting response and passing reference to RandomAddressAdapter, setting recyclerview to use this adapter instance
                RandomAddressAdapter randomAddressAdapter =new RandomAddressAdapter(addresses);
                rvMain.setAdapter(randomAddressAdapter);

                bInfo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<RandomAddress>> call, Throwable t) {
                Toast.makeText(RandomAddressActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}