package com.maiajam.sofa.data.models;

import java.util.Date;

public class NewOffers {

    String OfferName,RestName;
    int Id,cost ;
    Date date ;


    public NewOffers()
    {

    }

    public NewOffers(String cardOrderName, int amount, int cost) {



        this.cost = cost ;

    }


    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    //
    public void setOfferName(String name) {
        OfferName = name;
    }

    public String getOfferName() {
        return OfferName;
    }



    //


    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    //


    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    //


    public void setRestName(String restName) {
        RestName = restName;
    }

    public String getRestName() {
        return RestName;
    }
}
