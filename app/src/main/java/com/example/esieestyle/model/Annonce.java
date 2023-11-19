package com.example.esieestyle.model;

import android.widget.Button;

public class Annonce {

    private String productName;
    private String SellerName;
    private String productState;
    private float productPrice;
    private String annonceDate;
    private Button favorite_button;

    public  Annonce(){

    }
    public Annonce(String productName, String sellerName, String productState, float productPrice, String annonceDate, Button favorite_button) {
        this.productName = productName;
        this.SellerName = sellerName;
        this.productState = productState;
        this.productPrice = productPrice;
        this.annonceDate = annonceDate;
        this.favorite_button = favorite_button;
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

    public Button getFavorite_button() {
        return favorite_button;
    }
}
