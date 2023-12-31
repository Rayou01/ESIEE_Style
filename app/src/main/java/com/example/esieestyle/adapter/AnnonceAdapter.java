package com.example.esieestyle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esieestyle.R;
import com.example.esieestyle.RecyclerViewInterface;
import com.example.esieestyle.model.Annonce;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AnnonceAdapter extends FirestoreRecyclerAdapter<Annonce, AnnonceAdapter.AnnonceViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    public AnnonceAdapter(@NonNull FirestoreRecyclerOptions<Annonce> options, RecyclerViewInterface recyclerViewInterface) {
        super(options);
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    protected void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position, @NonNull Annonce model) {
        String priceString = model.getProductPrice() + " €";

        holder.textView_productName.setText(model.getProductName());
        holder.textView_sellerName.setText(model.getSellerName());
        holder.textView_productState.setText(model.getProductState());
        holder.textView_productPrice.setText(priceString);
        holder.textView_annonceDate.setText(model.getAnnonceDate());
    }

    @NonNull
    @Override
    public AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.annonce_view, parent, false);
        return new AnnonceViewHolder(v, recyclerViewInterface);
    }

    public class AnnonceViewHolder extends RecyclerView.ViewHolder{

        public TextView textView_productName;
        public TextView textView_sellerName;
        public TextView textView_productState;
        public TextView textView_productPrice;
        public TextView textView_annonceDate;

        public AnnonceViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textView_productName = itemView.findViewById(R.id.product_name_TextView);
            textView_sellerName = itemView.findViewById(R.id.seller_name_TextView);
            textView_productState = itemView.findViewById(R.id.product_state_TextView);
            textView_productPrice = itemView.findViewById(R.id.product_price_TextView);
            textView_annonceDate = itemView.findViewById(R.id.annonce_date_TextView);

            itemView.setOnClickListener(view -> {
                if(recyclerViewInterface != null){
                    int position = getAbsoluteAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        recyclerViewInterface.OnItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }
}
