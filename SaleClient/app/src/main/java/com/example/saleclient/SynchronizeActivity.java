package com.example.saleclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

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
                String json = "{'ProductName':'Test1','UnitPrice':1.11, 'VatRate':0}" +
                        "{'ProductName':'Test2','UnitPrice':22.0, 'VatRate':8}" +
                        "{'ProductName':'Test3','UnitPrice':12.0, 'VatRate':1}";
                try {
                    JSONObject obj = new JSONObject(json);
                    Log.d("My App", obj.toString());
                    Log.d("ProductName value ", obj.getString("ProductName"));
                } catch (Throwable tx) {
                    Log.e("App", "Could not parse malformed JSON: \"" + json + "\"");
                }

                String msg = "getProduct";
                MessageSender messageSender = new MessageSender();
                messageSender.execute(msg);
            }
        });

        sendSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DemoObject demo = new DemoObject(1, 1, 1, 1);
                JSONObject jo = null;
                try {
                    jo = demo.toJSON();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String msg = "saleDetails-" + jo.toString();
                Log.e("App", "Could not parse malformed JSON: \"" + jo.toString() + "\"");
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


    public class DemoObject {
        private int receiptCount;
        private double totalAmount;
        private double cashPayment;
        private double creditPayment;

        public DemoObject(int Count, double Amount, double cash, double credit) {
            receiptCount = Count;
            totalAmount = Amount;
            cashPayment = cash;
            creditPayment = credit;
        }

        public JSONObject toJSON() throws JSONException {
                JSONObject jo = new JSONObject();
                jo.put("receiptCount", 1);
                jo.put("totalAmount", 1);
                jo.put("cashPayment", 1);
                jo.put("creditPayment", 1);
                return jo;
        }
    }
}

