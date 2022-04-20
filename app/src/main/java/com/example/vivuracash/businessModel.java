package com.example.vivuracash;

import java.math.BigInteger;

public class businessModel {

   // private int logo;
    private String business_name_text;
    private  String business_contact_text;

    public businessModel( String business_name_text, String business_contact_text) {
       // this.logo = logo;
        this.business_name_text = business_name_text;
        this.business_contact_text = business_contact_text;
    }

    public int getLogo() {
//        return logo;
        //we put static logo for every business name and this can be changed later
        return R.drawable.img;
//        return  0;
    }

    public String getBusiness_name_text() {
        return business_name_text;
    }

    public String getBusiness_contact_text() {
        return business_contact_text;
    }
}
