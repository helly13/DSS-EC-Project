package com.example.sandburg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class Adp_Order extends RecyclerView.Adapter<Adp_Order.MyView> {

    private String TAG = "Adp_Order";
    private ArrayList<OrderModel>  list = new ArrayList<OrderModel>();
    private Context mContext;


    public class MyView extends RecyclerView.ViewHolder {


        public TextView textorderid, textitemname, texttotalqty,texttable;
        public Button btnAccept, btnReject;

        public MyView(View view) {
            super(view);

            textorderid = view.findViewById(R.id.textorderid);
            textitemname = view.findViewById(R.id.textitemname);
            texttotalqty =  view.findViewById(R.id.texttotalqty);
            texttable= view.findViewById(R.id.texttable);
            btnAccept= view.findViewById(R.id.btnAccept);
            btnReject= view.findViewById(R.id.btnReject);
        }
    }


    public Adp_Order(Context c) {
        this.mContext = c;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.textorderid.setText(list.get(position).orderID);
        holder.textitemname.setText(list.get(position).ItemName);
        holder.texttotalqty.setText(list.get(position).Qty);
        holder.texttable.setText(list.get(position).TableNo);

        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListOrder.CliclItems( position, 1);
            }
        });

        holder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListOrder.CliclItems( position, 2);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(ArrayList<OrderModel> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
}