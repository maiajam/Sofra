package com.maiajam.sofa.data.models;

public class FoodItem {

    String Name , Desc ;
    int Id,ItemCost,PreparingTime ;

    public FoodItem() {

    }



    public FoodItem(String name, String Desc, int _ItemCost, int _PreparingTime)
    {
        this.ItemCost = _ItemCost;
        this.PreparingTime = _PreparingTime;
       this.Desc = Desc ;
       this.Name = name;
    }
    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    //


    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getDesc() {
        return Desc;
    }

    //

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    //

    public int getItemCost() {
        return ItemCost;
    }

    public void setItemCost(int itemCost) {
        ItemCost = itemCost;
    }

    //


    public int getPreparingTime() {
        return PreparingTime;
    }

    public void setPreparingTime(int preparingTime) {
        PreparingTime = preparingTime;
    }
}
