package com.example.vivuracash;

public class businessModel {

    private int logo;
    private String business_name_text;
    private  String business_contact_text;

    public businessModel(int logo, String business_name_text, String business_contact_text) {
        this.logo = logo;
        this.business_name_text = business_name_text;
        this.business_contact_text = business_contact_text;
    }

    public int getLogo() {
        return logo;
    }

    public String getBusiness_name_text() {
        return business_name_text;
    }

    public String getBusiness_contact_text() {
        return business_contact_text;
    }
}
