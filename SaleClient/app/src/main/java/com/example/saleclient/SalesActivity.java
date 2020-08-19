package com.example.saleclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    ArrayList<Product> product = new ArrayList<>();
    public ArrayList<ArrayList<String>> nameList = new ArrayList<>();
    ArrayList<ArrayList<Double>> priceList = new ArrayList<>();
    ArrayList<ArrayList<Integer>> quantityList = new ArrayList<>();
    ArrayList<Double> priceArray;
    public ArrayList<String> nameArray;
    public ArrayList<Integer> quantityArray;
    RecyclerView recyclerView;
    Context context = this;
    public double paymentCash, paymentCredit = 0;
    public int receiptCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        String jsonProduct = ServerThread.incomingMessage;
        Log.e("Json Product", "\"" + jsonProduct + "\"");
        if (!jsonProduct.equals("ConnectionSuccess")) {
            try {
                JSONObject productJsonObject = new JSONObject(jsonProduct);
                JSONArray jsonArray = productJsonObject.getJSONArray("products");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject productObj = jsonArray.getJSONObject(i);
                    product.add(new Product(productObj.getString("ProductName"),
                            Double.parseDouble(productObj.getString("UnitPrice"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Json Product", "Could not parse malformed JSON: \"" + jsonProduct + "\"");
            }
        }

        recyclerView = findViewById(R.id.recyclerView);
        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonCredit = findViewById(R.id.buttonCredit);
        Button buttonCash = findViewById(R.id.buttonCash);
        TextView textViewTotalAmount = findViewById(R.id.textViewTotalAmount);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 3,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true); // Used to improve performance. Optional
        final ProductAdapter productAdapter = new ProductAdapter(product, context);
        recyclerView.setAdapter(productAdapter);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("saleData", "\"" + nameList.toString() + "\"");
                Log.e("payments", "\"" + "Nakit: " + paymentCash + "  Kredi: " + paymentCredit + "\"");
                String msg = "Ürünler:" + nameList.toString() +
                        "\nNakit:" + paymentCash + "  Kredi:" + paymentCredit;
                Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(SalesActivity.this, SynchronizeActivity.class);
                intent.putExtra("nameList", nameList);
                intent.putExtra("quantityArray", quantityArray);
                intent.putExtra("receiptCount", receiptCount);
                intent.putExtra("paymentCash", paymentCash);
                intent.putExtra("paymentCredit", paymentCredit);
                startActivity(intent);
            }
        });

        buttonCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentCredit = +productAdapter.roundTotalAmount;

                if (paymentCredit > 0) {
                    nameArray = new ArrayList<>();
                    priceArray = new ArrayList<>();
                    quantityArray = new ArrayList<>();
                    for (int i = 0; i < productAdapter.productNameArrayList.size(); i++) {
                        nameArray.add(productAdapter.productNameArrayList.get(i));
                        priceArray.add(productAdapter.productPriceArrayList.get(i));
                        quantityArray.add(productAdapter.totalQuantityArrayList.get(i));
                    }
                    nameList.add(nameArray);
                    priceList.add(priceArray);
                    quantityList.add(quantityArray);
                    receiptCount += 1;
                    Log.e("nameList", "\"" + nameList.toString() + "\"");
                    Log.e("priceList", "\"" + priceList.toString() + "\"");
                    Log.e("quantityList", "\"" + quantityList.toString() + "\"");
                    productAdapter.productNameArrayList.clear();
                    productAdapter.productPriceArrayList.clear();
                    productAdapter.totalQuantityArrayList.clear();

                    productAdapter.shoppingCartTotalAmount = 0;
                    productAdapter.textViewTotalAmount.setText("0.0 TL");
                    String msg = "KREDI --> " + paymentCredit + " TL\nÖdeme Tamamlandı.";
                    Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String msg = "Ürün Eklemediniz. Ödeme Başarısız.";
                    Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        buttonCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentCash = +productAdapter.roundTotalAmount;

                if (paymentCash > 0) {
                    nameArray = new ArrayList<>();
                    priceArray = new ArrayList<>();
                    quantityArray = new ArrayList<>();
                    for (int i = 0; i < productAdapter.productNameArrayList.size(); i++) {
                        nameArray.add(productAdapter.productNameArrayList.get(i));
                        priceArray.add(productAdapter.productPriceArrayList.get(i));
                        quantityArray.add(productAdapter.totalQuantityArrayList.get(i));
                    }
                    nameList.add(nameArray);
                    priceList.add(priceArray);
                    quantityList.add(quantityArray);
                    receiptCount += 1;
                    Log.e("nameList", "\"" + nameList.toString() + "\"");
                    Log.e("priceList", "\"" + priceList.toString() + "\"");
                    Log.e("quantityList", "\"" + quantityList.toString() + "\"");
                    productAdapter.productNameArrayList.clear();
                    productAdapter.productPriceArrayList.clear();
                    productAdapter.totalQuantityArrayList.clear();

                    productAdapter.shoppingCartTotalAmount = 0;
                    productAdapter.textViewTotalAmount.setText("0.0 TL");
                    String msg = "NAKIT --> " + paymentCash + "\nÖdeme Tamamlandı.";
                    Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String msg = "Ürün Eklemediniz. Ödeme Başarısız.";
                    Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
