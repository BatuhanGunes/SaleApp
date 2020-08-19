package com.example.saleclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                String msg = "getProduct-null";
                MessageSender messageSender = new MessageSender();
                messageSender.execute(msg);

                String msgToast = "Ürünler indirildi.";
                Toast toast = Toast.makeText(getApplicationContext(), msgToast, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        sendSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Bundle extras = getIntent().getExtras();
                    if (extras.getStringArrayList("nameList").size() > 0) {
                        //Detail Table
                        String extrasNameList = extras.get("nameList").toString();
                        extrasNameList = extrasNameList.replace("[", "");
                        extrasNameList = extrasNameList.substring(0, extrasNameList.length() - 2);
                        String s1[] = extrasNameList.split("], ");

                        for (int i = 0; i < s1.length; i++) {
                            String productName[] = s1[i].split(", ");//separating
                            for (int j = 0; j < productName.length; j++) {
                                JSONObject jsonDetails = new JSONObject();
                                jsonDetails.put("productName", productName[j]);
                                jsonDetails.put("totalQuantity", extras.getIntegerArrayList("quantityArray").get(j));

                                Log.e("DetailsTable", "\"" + jsonDetails.toString() + "\"");
                                String msgDetails = "saleDetails-" + jsonDetails.toString();
                                MessageSender messageSenderDetails = new MessageSender();
                                messageSenderDetails.execute(msgDetails);
                            }
                        }
                        //sale table
                        JSONObject jsonSale = new JSONObject();
                        jsonSale.put("receiptCount", extras.getInt("receiptCount"));
                        jsonSale.put("cashPayment", extras.getDouble("paymentCash"));
                        jsonSale.put("creditPayment", extras.getDouble("paymentCredit"));

                        Log.e("SaleTable", "\"" + jsonSale.toString() + "\"");
                        String msg = "sale-" + jsonSale.toString();
                        MessageSender messageSenderSale = new MessageSender();
                        messageSenderSale.execute(msg);
                    } else {
                        String msg = "Henüz ürün satın almadınız. Gönderim başarısız.";
                        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    }
}

