package com.example.saleclient;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> productArrayList;
    ArrayList<String> productNameArrayList = new ArrayList<>();
    ArrayList<Double> productPriceArrayList = new ArrayList<>();
    ArrayList<Integer> totalQuantityArrayList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;
    TextView textViewTotalAmount;
    double roundTotalAmount;
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
        holder.textViewProductName.setText(productArrayList.get(position).getProductName());
        textViewTotalAmount = ((Activity) context).findViewById(R.id.textViewTotalAmount);
        holder.linearLayout.setTag(holder);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHolder holder = (ViewHolder) view.getTag();
                int position = holder.getAdapterPosition();
                String productName = productArrayList.get(position).getProductName();
                double productPrice = productArrayList.get(position).getProductPrice();

                // checks if there is the same value
                boolean setFlag = false;
                int setIndex = 0;
                for (int i = 0; i < productNameArrayList.size(); i++) {
                    if (productNameArrayList.get(i) == productName) {
                        setFlag = true;
                        setIndex = i;
                    }
                }

                // If there is no same value, it adds new data. If available, it updates the existing data.
                if(setFlag == false){
                    productNameArrayList.add(productName);
                    productPriceArrayList.add(productPrice);
                    totalQuantityArrayList.add(1);
                }else {
                    productPriceArrayList.set(setIndex, (productPrice * 2));
                    totalQuantityArrayList.set(setIndex, (totalQuantityArrayList.get(setIndex) + 1));
                }

                shoppingCartTotalAmount += productPrice;
                roundTotalAmount = new BigDecimal(shoppingCartTotalAmount).setScale(
                        2, BigDecimal.ROUND_HALF_UP).doubleValue();
                textViewTotalAmount.setText(roundTotalAmount + " TL");

                String msg = "Eklenen Ürün: \n" + productName + " --> " + productPrice
                        + " TL\nToplam Fiyat --> " + roundTotalAmount + " Tl";
                Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public ArrayList<Integer> getTotalQuantityArrayList() {
        return totalQuantityArrayList;
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView textViewProductName, textViewProductPrice, textViewPriceTitle, textViewPriceType;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layout);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
            textViewPriceType = itemView.findViewById(R.id.textViewPriceType);
        }
    }


}


