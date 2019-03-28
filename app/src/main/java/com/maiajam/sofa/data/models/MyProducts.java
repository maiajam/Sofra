package com.maiajam.sofa.data.models;

public class MyProducts {

    String Name,Desc;
    int Id,Cost;

   public MyProducts()
    {

    }

    public MyProducts(String itemName, String itemDescription, int itemCost) {

       this.Name = itemName ;
       this.Cost = itemCost ;
       this.Desc = itemDescription ;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    //


    public void setCost(int cost) {
        Cost = cost;
    }

    public int getCost() {
        return Cost;
    }

    //


    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    //


    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getDesc() {
        return Desc;
    }
}
