package com.example.saleclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SynchronizeActivity extends AppCompatActivity {

    private Button buyProducts, sendSales, sellProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronize);

        buyProducts = (Button)findViewById(R.id.buttonBuyProducts);
        sendSales = (Button)findViewById(R.id.buttonSendSales);
        sellProduct = (Button)findViewById(R.id.buttonSellProduct);

        buyProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sendSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sellProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
