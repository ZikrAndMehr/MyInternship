package com.zam.myinternship;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llMain;
    private TextInputLayout tilNumber;
    private EditText etNumber;
    private Button bFetchData;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llMain=findViewById(R.id.ll_main);
        tilNumber=findViewById(R.id.til_number);
        etNumber=findViewById(R.id.et_number);
        bFetchData=findViewById(R.id.b_fetch_data);

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
    }
}