package com.example.esieestyle.model;

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
}
