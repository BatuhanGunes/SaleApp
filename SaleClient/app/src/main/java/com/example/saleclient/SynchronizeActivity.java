package com.example.saleclient;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SynchronizeActivity extends AppCompatActivity {

    private Button buyProducts, sendSales, sellProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronize);

        buyProducts = findViewById(R.id.buttonBuyProducts);
        sendSales = findViewById(R.id.buttonSendSales);
        sellProduct = findViewById(R.id.buttonSellProduct);

        buyProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Test Values
                String json =   "{'ProductName':'Test1','UnitPrice':1.11, 'VatRate':0}" +
                                "{'ProductName':'Test2','UnitPrice':22.0, 'VatRate':8}" +
                                "{'ProductName':'Test3','UnitPrice':12.0, 'VatRate':1}";
                try {
                    JSONObject obj = new JSONObject(json);
                    Log.d("My App", obj.toString());
                    Log.d("ProductName value ", obj.getString("ProductName"));
                } catch (Throwable tx) {
                    Log.e("My App", "Could not parse malformed JSON: \"" + json + "\"");
                }

                String msg = "downloadProducts";
                MessageSender messageSender = new MessageSender();
                messageSender.execute(msg);
            }
        });

        sendSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Test values
                int productId = 11;
                String productName = "Test1";
                int quantity = 11;
                double amount = 11.0;

                String msg = "uploadSale-" + productId + "-" + productName + "-" + quantity + "-" + amount;
                MessageSender messageSender = new MessageSender();
                messageSender.execute(msg);
            }
        });

        sellProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SynchronizeActivity.this, SalesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Start communication. Constantly reads data from the server
        Thread conThread = new Thread(new ServerThread());
        conThread.start();
    }
}
