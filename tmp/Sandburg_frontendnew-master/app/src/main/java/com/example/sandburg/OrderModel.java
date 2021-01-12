package com.example.sandburg;

public class OrderModel {

    public String orderID;
    public String ItemName;
    public String Qty;
    public String TableNo;


    public OrderModel(String orderID, String ItemName,String Qty,String TableNo) {
        this.orderID = orderID;
        this.ItemName = ItemName;
        this.Qty = Qty;
        this.TableNo = TableNo;

    }
}
