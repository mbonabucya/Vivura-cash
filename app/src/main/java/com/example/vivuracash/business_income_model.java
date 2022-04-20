package com.example.vivuracash;

public class business_income_model {

    private int id;
    private String item;
    private String amount;
    private String reason;
    private String date;

    public business_income_model(int id, String item, String amount, String reason, String date) {
        this.id = id;
        this.item = item;
        this.amount = amount;
        this.reason = reason;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
