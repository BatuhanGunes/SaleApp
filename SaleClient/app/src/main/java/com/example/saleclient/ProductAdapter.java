package com.example.saleclient;

import android.app.Activity;
import android.content.Context;
import android.icu.number.Precision;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> productArrayList;
    LayoutInflater layoutInflater;
    Context context;
    TextView textViewTotalAmount;
    double shoppingCartTotalAmount = 0;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public ProductAdapter(ArrayList<Product> productArrayList, Context context) {
        this.productArrayList = productArrayList;
        this.context = context;
    }

    // For each card the interface to be represented is selected
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.product_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewProductPrice.setText(Double.toString(productArrayList.get(position).getProductPrice()));
        holder.checkBoxProductName.setText(productArrayList.get(position).getProductName());
        holder.checkBoxProductName.setTag(holder);
        holder.checkBoxProductName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHolder holder = (ViewHolder) view.getTag();
                int position = holder.getAdapterPosition();
                String productName = productArrayList.get(position).getProductName();
                double productPrice = productArrayList.get(position).getProductPrice();
                textViewTotalAmount = (TextView) ((Activity) context).findViewById(R.id.textViewTotalAmount);

                if (holder.checkBoxProductName.isChecked()) {
                    shoppingCartTotalAmount += productArrayList.get(position).getProductPrice();
                    double roundTotalAmount = new BigDecimal(shoppingCartTotalAmount).setScale(
                            2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    textViewTotalAmount.setText(roundTotalAmount + " TL");
                } else {
                    shoppingCartTotalAmount -= productArrayList.get(position).getProductPrice();
                    double roundTotalAmount = new BigDecimal(shoppingCartTotalAmount).setScale(
                            2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    textViewTotalAmount.setText(roundTotalAmount + " TL");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBoxProductName;
        TextView textViewProductPrice, textViewPriceTitle, textViewPriceType;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBoxProductName = itemView.findViewById(R.id.checkBoxProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
            textViewPriceTitle = itemView.findViewById(R.id.textViewPriceTitle);
            textViewPriceType = itemView.findViewById(R.id.textViewPriceType);
        }
    }
}
