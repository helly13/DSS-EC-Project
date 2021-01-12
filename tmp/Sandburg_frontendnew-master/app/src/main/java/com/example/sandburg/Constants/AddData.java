package com.example.sandburg.Constants;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AddData {

    @SerializedName("_id")
    public Object _id;

    @SerializedName("Menu")
    public MenuData Menu;

    @SerializedName("Category")
    public CategoryData Category;

    @SerializedName("Total_bill_amount")
    public String Total_bill_amount;

    @SerializedName("Total_qty")
    public String Total_qty;

    @SerializedName("Table_no")
    public String Table_no;

    @SerializedName("Status")
    public String Status;
}
