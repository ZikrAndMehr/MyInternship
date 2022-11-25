package com.zam.myinternship;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llMain;
    private TextInputLayout tilNumber;
    private EditText etNumber;
    private Button bFetchData;
    private int number;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llMain=findViewById(R.id.ll_main);
        tilNumber=findViewById(R.id.til_number);
        etNumber=findViewById(R.id.et_number);
        bFetchData=findViewById(R.id.b_fetch_data);
        tvData=findViewById(R.id.tv_data);

        bFetchData.setOnClickListener(v -> {
            if (checkNumberRange()) fetchData();
        });
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

        if (5<=number && number<=20) {
            Toast.makeText(this, R.string.fetching, Toast.LENGTH_SHORT).show();
            tilNumber.setErrorEnabled(false);
        }

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MyAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI myAPI=retrofit.create(MyAPI.class);

        Call<List<RandomAddress>> call=myAPI.getRandomAddresses();

        call.enqueue(new Callback<List<RandomAddress>>() {
            @Override
            public void onResponse(Call<List<RandomAddress>> call, Response<List<RandomAddress>> response) {
                List<RandomAddress> addresses=response.body();

                String s="";

                for (int i=0; i<addresses.size(); i++) {
                    s+=addresses.get(i).getCountry()+"\n";
                }

                tvData.setText(s);
            }

            @Override
            public void onFailure(Call<List<RandomAddress>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                tvData.setText(t.getMessage());
            }
        });
    }
}