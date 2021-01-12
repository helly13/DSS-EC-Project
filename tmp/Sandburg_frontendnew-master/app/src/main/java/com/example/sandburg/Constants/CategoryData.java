package com.example.sandburg.Constants;

import com.google.gson.annotations.SerializedName;

public class CategoryData {

    @SerializedName("Category_Id")
    public String Category_Id;

    @SerializedName("Category_Name")
    public String Category_Name;


    public String toString(){
        return "Category_Id: " + Category_Id + "  " + "Category_name:" + Category_Name;
    }
}
