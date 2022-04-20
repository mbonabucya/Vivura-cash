package com.example.vivuracash;

public class Income_Business_Model {

    private int id;
    private String item;
    private String amount;
    private String reason;
    private String italiki;





    Income_Business_Model(int id, String amount, String reason, String italiki, String item){

        this.id=id;
        this.amount=amount;
        this.reason=reason;
        this.italiki=italiki;
        this.item=item;
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

    public String getItaliki() {
        return italiki;
    }

    public void setItaliki(String italiki) {
        this.italiki = italiki;
    }

}

