package com.maiajam.sofa.data.models;

public class CartOrders {

    String Name;
    int Id,cost,Amount ;

    public CartOrders()
    {

    }

    public CartOrders(int id, String cardOrderName, int amount, int cost) {

        this.Id = id ;
        this.Amount = amount ;
        this.cost = cost ;
        this.Name = cardOrderName ;
    }


    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    //
    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    //


    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getAmount() {
        return Amount;
    }

    //


    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }


}
