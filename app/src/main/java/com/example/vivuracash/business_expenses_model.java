package com.example.vivuracash;

public class business_expenses_model {
    private int id;
    private String activity;
    private String amount;
    private String description;
    private String date;


    public business_expenses_model(int id, String activity, String amount, String description, String date) {
        this.id = id;
        this.activity = activity;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
