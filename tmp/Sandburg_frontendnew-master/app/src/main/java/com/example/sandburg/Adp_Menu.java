package com.example.sandburg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class Adp_Menu extends RecyclerView.Adapter<Adp_Menu.MyView> {

    private String TAG = "Adp_Menu";
    private ArrayList<MenuModel>  list = new ArrayList<MenuModel>();
    private Context mContext;


    public class MyView extends RecyclerView.ViewHolder {


        public TextView  textcatid,textcatname,textprice, textitem_name, textstatus, texttype;
//        public ListView categoryListView;
        public Button btnupdate, btndelete;

        public MyView(View view) {
            super(view);

          textcatid = view.findViewById(R.id.textcatid);
            textcatname = view.findViewById(R.id.textcatname);
            textprice =  view.findViewById(R.id.textprice);
            textitem_name= view.findViewById(R.id.textitem_name);
            textstatus= view.findViewById(R.id.textstatus);
            texttype= view.findViewById(R.id.texttype);
            btnupdate= view.findViewById(R.id.btnupdate);
            btndelete= view.findViewById(R.id.btndelete);
        }
    }


    public Adp_Menu(Context c) {
        this.mContext = c;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

//        holder.categoryListView.setAdapter((ListAdapter) list.get(position).categoryList);
       holder.textcatid.setText(list.get(position).CatId);
        holder.textcatname.setText(list.get(position).CatName);
        holder.textprice.setText(list.get(position).Price);
        holder.textitem_name.setText(list.get(position).itemName);
        holder.textstatus.setText(list.get(position).Stat);
        holder.texttype.setText(list.get(position).Type);


        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListMenu.CliclItems( position, 1);
            }
        });

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListMenu.CliclItems( position, 2);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(ArrayList<MenuModel> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
}