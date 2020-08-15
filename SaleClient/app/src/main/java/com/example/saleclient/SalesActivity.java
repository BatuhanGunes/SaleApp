package com.example.saleclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SalesActivity extends AppCompatActivity {

    private Button buttonBack, buttonCredit, buttonCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonCredit = (Button)findViewById(R.id.buttonCredit);
        buttonCash = (Button)findViewById(R.id.buttonCash);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
