package com.example.sandburg.Constants;

import com.google.gson.annotations.SerializedName;
import java.util.*;

public class AddData1 {


   @SerializedName("Category")
    public List<CategoryData> categoryList;

    @SerializedName("Item_name")
    public String Item_name;

    @SerializedName("Price")
    public String Price;


    @SerializedName("Status")
    public String Status;

    @SerializedName("Type_of_Food")
    public String Type_of_Food;

}
