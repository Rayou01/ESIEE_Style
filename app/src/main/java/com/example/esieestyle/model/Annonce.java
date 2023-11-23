package com.example.esieestyle.model;

import android.os.Bundle;

public class Annonce {

    private String productName;
    private String SellerName;
    private String productState;
    private float productPrice;
    private String annonceDate;

    public  Annonce(){

    }
    public Annonce(String productName, String sellerName, String productState, float productPrice, String annonceDate) {
        this.productName = productName;
        this.SellerName = sellerName;
        this.productState = productState;
        this.productPrice = productPrice;
        this.annonceDate = annonceDate;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("productName", productName);
        bundle.putString("sellerName", SellerName);
        bundle.putString("productState", productState);
        bundle.putFloat("productPrice", productPrice);
        bundle.putString("annonceDate", annonceDate);
        return bundle;
    }

    public static Annonce fromBundle(Bundle bundle) {
        Annonce annonce = new Annonce();
        annonce.setProductName(bundle.getString("productName"));
        annonce.setSellerName(bundle.getString("sellerName"));
        annonce.setProductState(bundle.getString("productState"));
        annonce.setProductPrice(bundle.getInt("productPrice"));
        annonce.setAnnonceDate(bundle.getString("annonceDate"));
        return annonce;
    }

    public String getProductName() {
        return productName;
    }

    public String getSellerName() {
        return SellerName;
    }

    public String getProductState() {
        return productState;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public String getAnnonceDate() {
        return annonceDate;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSellerName(String sellerName) {
        SellerName = sellerName;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public void setAnnonceDate(String annonceDate) {
        this.annonceDate = annonceDate;
    }
}
