package com.example.saleclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SalesActivity extends AppCompatActivity {

    private Button buttonBack, buttonCredit, buttonCash;
    private TextView textViewTotalAmount;
    ArrayList<Product> product = new ArrayList<>();
    RecyclerView recyclerView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        String jsonProduct = ServerThread.jsonProduct;
        try {
            JSONObject productJsonObject = new JSONObject(jsonProduct);
            JSONArray jsonArray = productJsonObject.getJSONArray("products");

            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject productObj = jsonArray.getJSONObject(i);
                product.add(new Product(productObj.getString("ProductName"),
                        Double.parseDouble(productObj.getString("UnitPrice"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Json Product", "Could not parse malformed JSON: \"" + jsonProduct + "\"");
        }

        recyclerView = findViewById(R.id.recyclerView);
        buttonBack = findViewById(R.id.buttonBack);
        buttonCredit = findViewById(R.id.buttonCredit);
        buttonCash = findViewById(R.id.buttonCash);
        textViewTotalAmount = findViewById(R.id.textViewTotalAmount);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 3,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true); // Used to improve performance. Optional
        final ProductAdapter productAdapter = new ProductAdapter(product, context);
        recyclerView.setAdapter(productAdapter);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesActivity.this, SynchronizeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double payment = productAdapter.roundTotalAmount;

                Receipt receipt = new Receipt();
                for (int i = 0; i < productAdapter.productNameArrayList.size(); i++) {
                    receipt.tagName = productAdapter.productNameArrayList.get(i);
                }
                for (int i = 0; i < productAdapter.productPriceArrayList.size(); i++) {
                    receipt.tagPrice = productAdapter.productPriceArrayList.get(i);
                }
                receipt.tagName = "TOTAL";
                receipt.tagPrice = payment;
                receipt.tagName = "CREDIT";
                receipt.tagPrice = payment;

                ReceiptList receiptList = new ReceiptList();
                receiptList.receipts.add(receipt);

                if(payment > 0){
                    String msg = "KREDI --> " + payment + " TL\nÖdeme Tamamlandı.";
                    Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    String msg = "Ürün Eklemediniz. Ödeme Başarısız.";
                    Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        buttonCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double payment = productAdapter.roundTotalAmount;

                Receipt receipt = new Receipt();
                for (int i = 0; i < productAdapter.productNameArrayList.size(); i++) {
                    receipt.tagName = productAdapter.productNameArrayList.get(i);
                }
                for (int i = 0; i < productAdapter.productPriceArrayList.size(); i++) {
                    receipt.tagPrice = productAdapter.productPriceArrayList.get(i);
                }
                receipt.tagName = "TOTAL";
                receipt.tagPrice = payment;
                receipt.tagName = "CASH";
                receipt.tagPrice = payment;

                ReceiptList receiptList = new ReceiptList();
                receiptList.receipts.add(receipt);

                if(payment > 0){
                    String msg = "NAKIT --> " + payment + "\nÖdeme Tamamlandı.";
                    Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    String msg = "Ürün Eklemediniz. Ödeme Başarısız.";
                    Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    class Receipt{
        String tagName;
        double tagPrice;
    }

    class ReceiptList{
        ArrayList<Receipt> receipts = new ArrayList<>();
    }
}
