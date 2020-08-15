package com.example.saleclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SalesActivity extends AppCompatActivity {

    private Button buttonBack, buttonCredit, buttonCash;
    public TextView textViewTotalAmount;
    ArrayList<Product> product = new ArrayList<>();
    RecyclerView recyclerView;
    Context context = this;

    public TextView getTextViewTotalAmount() {
        return textViewTotalAmount;
    }

    public void setTextViewTotalAmount(TextView textViewTotalAmount) {
        this.textViewTotalAmount = textViewTotalAmount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        // Değiştirilecek.... test amaçlı el ile girildi.
        product.add(new Product("DenemeName", 10.11));
        product.add(new Product("DenemeName2", 10.22));
        product.add(new Product("DenemeName2", 10.33));
        product.add(new Product("DenemeName2", 10.44));
        product.add(new Product("DenemeName2", 10.55));
        product.add(new Product("DenemeName2", 10.66));
        product.add(new Product("DenemeName2", 10.77));
        product.add(new Product("DenemeName2", 10.88));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonCredit = (Button) findViewById(R.id.buttonCredit);
        buttonCash = (Button) findViewById(R.id.buttonCash);
        textViewTotalAmount = (TextView) findViewById(R.id.textViewTotalAmount);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true); // Used to improve performance. Optional
        ProductAdapter productAdapter = new ProductAdapter(product, context);
        recyclerView.setAdapter(productAdapter);

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
