package com.example.headphones;

import java.io.Serializable;

public class Headphones implements Serializable {
    private String itemName;
    private double itemPrice;
    private String itemimg;

    public Headphones() {
    }

    public Headphones(String itemName, double itemPrice, String itemimg) {
        this.itemName = itemName;
        this.itemimg = itemimg;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemimg() {
        return itemimg;
    }

    public void setItemimg(String itemimg) {
        this.itemimg = itemimg;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
