package com.example.esieestyle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esieestyle.R;
import com.example.esieestyle.model.Annonce;

import java.util.ArrayList;

public class AnnonceAdapter extends RecyclerView.Adapter<AnnonceAdapter.AnnonceViewHolder> {

    private ArrayList<Annonce> annonces;
    private Context context;

    public AnnonceAdapter(Context context) {
        this.context = context;
        this.annonces = new ArrayList<>();
    }

    public void add(Annonce annonce) {
        annonces.add(annonce);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.annonce_view, parent, false);
        return new AnnonceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position) {
        Annonce annonce = annonces.get(position);
        holder.textView_productName.setText(annonce.getProductName());
        holder.textView_sellerName.setText(annonce.getSellerName());
        holder.textView_productState.setText(annonce.getProductState());
        holder.textView_productPrice.setText(String.valueOf(annonce.getProductPrice()));
        holder.textView_annonceDate.setText(String.valueOf(annonce.getAnnonceDate()));
    }

    @Override
    public int getItemCount() {
        return annonces.size();
    }

    public static class AnnonceViewHolder extends RecyclerView.ViewHolder{

        public TextView textView_productName;
        public TextView textView_sellerName;
        public TextView textView_productState;
        public TextView textView_productPrice;
        public TextView textView_annonceDate;
        public Button favorite_button;

        public AnnonceViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_productName = itemView.findViewById(R.id.product_name_TextView);
            textView_sellerName = itemView.findViewById(R.id.seller_name_TextView);
            textView_productState = itemView.findViewById(R.id.product_state_TextView);
            textView_productPrice = itemView.findViewById(R.id.product_price_TextView);
            textView_annonceDate = itemView.findViewById(R.id.annonce_date_TextView);
        }
    }
}
