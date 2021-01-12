package com.example.sandburg;

public class MenuModel {

    public String CatId;
   public String CatName;
    public String Price;
    public String itemName;
    public String Stat;
    public String Type;

    public MenuModel(String CatId,String CatName,String Price, String itemName,String Stat,String Type) {
       this.CatId = CatId;
        this.CatName = CatName;
        this.Price = Price;
        this.itemName = itemName;
        this.Stat = Stat;
        this.Type = Type;
    }
}
