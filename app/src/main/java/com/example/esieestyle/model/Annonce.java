package com.example.esieestyle.model;

import java.util.Date;

public class Annonce {

    private String productName;
    private String SellerName;
    private String productState;
    private float productPrice;
    private Date annonceDate;

    public  Annonce(){

    }
    public Annonce(String productName, String sellerName, String productState, float productPrice, Date annonceDate) {
        this.productName = productName;
        this.SellerName = sellerName;
        this.productState = productState;
        this.productPrice = productPrice;
        this.annonceDate = annonceDate;
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

    public Date getAnnonceDate() {
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

    public void setAnnonceDate(Date annonceDate) {
        this.annonceDate = annonceDate;
    }
}
