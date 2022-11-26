package com.zam.myinternship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL="https://random-data-api.com/api/";

    private LinearLayout llMain;
    private TextInputLayout tilNumber;
    private EditText etNumber;
    private Button bFetchData, bInfo;
    private RecyclerView rvMain;
    private int number=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llMain=findViewById(R.id.ll_main);
        tilNumber=findViewById(R.id.til_number);
        etNumber=findViewById(R.id.et_number);
        bFetchData=findViewById(R.id.b_fetch_data);
        rvMain=findViewById(R.id.rv_main);
        bInfo=findViewById(R.id.b_info);

        bFetchData.setOnClickListener(v -> {
            if (checkNumberRange()) fetchData();
        });

        rvMain.setLayoutManager(new LinearLayoutManager(this));
    }

    private boolean checkNumberRange() {
        try {
            number=Integer.parseInt(etNumber.getText().toString());
            if (number<5 || 20<number) throw new Exception("NumberOutOfRange");
            return true;
        } catch (Exception e) {
            tilNumber.setErrorEnabled(true);
            if (e instanceof NumberFormatException) tilNumber.setError(getString(R.string.number_null));
            else tilNumber.setError(getString(R.string.number_range));
            return false;
        }
    }

    private void fetchData() {

        Toast.makeText(this, R.string.fetching, Toast.LENGTH_SHORT).show();
        tilNumber.setErrorEnabled(false);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI myAPI=retrofit.create(MyAPI.class);

        Call<ArrayList<RandomAddress>> call=myAPI.getRandomAddresses(BASE_URL+"address/random_address?size="+number);

        call.enqueue(new Callback<ArrayList<RandomAddress>>() {
            @Override
            public void onResponse(Call<ArrayList<RandomAddress>> call, Response<ArrayList<RandomAddress>> response) {
                ArrayList<RandomAddress> addresses=response.body();
                MyAdapter myAdapter=new MyAdapter(addresses);
                rvMain.setAdapter(myAdapter);

                bInfo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<RandomAddress>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}