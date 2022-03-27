package com.example.vivuracash;

public class personal_expenses_Model {
    private int id;
    private String activity;
    private String amount;
    private String description;
    private String italiki;


    public personal_expenses_Model(int id, String activity, String amount, String description, String italiki) {
        this.id=id;
        this.activity=activity;
        this.amount=amount;
        this.description=description;
        this.italiki=italiki;


    }

    public int getId() {
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

    public String getItaliki() {
        return italiki;
    }

    public void setItaliki(String italiki) {
        this.italiki = italiki;
    }
}
