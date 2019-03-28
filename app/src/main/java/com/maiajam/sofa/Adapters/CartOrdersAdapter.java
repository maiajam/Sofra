package com.maiajam.sofa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maiajam.sofa.R;

import java.util.ArrayList;

import com.maiajam.sofa.data.models.CartOrders;

public class CartOrdersAdapter extends RecyclerView.Adapter<CartOrdersAdapter.holder> implements View.OnClickListener {

    Context context ;
    ArrayList<CartOrders> CartOrderList ;
    CartOrders cartOrdersObj;
    holder h ;
    String Name;
    int Id,cost,Amount,ToatalPrice;

    public CartOrdersAdapter(Context context , ArrayList<CartOrders> list)
    {

        this.context = context ;
        this.CartOrderList = list ;

    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardorder_my_row,null);
        h = new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        cartOrdersObj = CartOrderList.get(position);

        cost = cartOrdersObj.getCost();
        Name = cartOrdersObj.getName();
        Amount = cartOrdersObj.getAmount();
        Id = cartOrdersObj.getId();


        holder.Amount_ed.setText(String.valueOf(Amount));
        holder.ItemPrice_txt.setText(String.valueOf(cost));
        holder.OrderName_txt.setText(Name);


        holder.ToatalPrice_txt.setText(String.valueOf(cost*Amount));


        holder.Increase_b.setOnClickListener(this);
        holder.Decrease_b.setOnClickListener(this);

    }



    @Override
    public int getItemCount() {
        return CartOrderList.size();
    }

    @Override
    public void onClick(View v) {
        if(v == h.Increase_b)
        {
            int amount = Integer.parseInt(h.Amount_ed.getText().toString());
            amount ++ ;
            h.Amount_ed.setText(String.valueOf(amount));

        }else if( v == h.Decrease_b)
        {
            int amount = Integer.parseInt(h.Amount_ed.getText().toString());
            if(amount> 1)
            {
                amount -- ;

                h.Amount_ed.setText(String.valueOf(amount));
            }else
            {
                Toast.makeText(context,"لقد وصلت للحد الادني للكمية",Toast.LENGTH_LONG).show();

            }

        }

    }


    public class holder extends RecyclerView.ViewHolder {


        TextView OrderName_txt,ToatalPrice_txt,ItemPrice_txt;
        Button Increase_b,Decrease_b;
        EditText Amount_ed;

        ImageView ItemPhoto_img ;

        public holder(View itemView) {

            super(itemView);

            OrderName_txt = (TextView)itemView.findViewById(R.id.OrderItemName_CardOrder_row_TXT);
            ToatalPrice_txt= (TextView)itemView.findViewById(R.id.Totalvalue_CardOrder_row_TXT);
            ItemPrice_txt= (TextView)itemView.findViewById(R.id.Cost_CardOrder_row_TXT);

            Increase_b = (Button)itemView.findViewById(R.id.Increse_CardOrder_row_B);
            Decrease_b = (Button)itemView.findViewById(R.id.Decrese_CardOrder_row_B);

            Amount_ed = (EditText)itemView.findViewById(R.id.AmountValue_CardOrder_row_Ed);

           ItemPhoto_img = (ImageView) itemView.findViewById(R.id.OrderItemIcon_CardOrder_row_img);

        }
    }

}
