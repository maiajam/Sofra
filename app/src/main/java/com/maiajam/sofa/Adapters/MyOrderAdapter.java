package com.maiajam.sofa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maiajam.sofa.R;

import java.util.List;

import com.maiajam.sofa.data.models.confirmOrder.ConfirmOrder;
import com.maiajam.sofa.data.models.declineOrder.DeclineOrder;
import com.maiajam.sofa.data.models.myOrders.Datum;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.holder> implements View.OnClickListener {

    Context _context ;
    List<Datum> MyOrderList ;
    int Type,Order_id;
    holder h ;
    ApiServeces apiServeces ;

    public MyOrderAdapter(Context context , List<Datum> list, int type)
    {
       _context = context ;
       MyOrderList = list ;
       Type = type ;

    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.currentorder_my_row,parent,false);
        h = new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        Datum myOrders = MyOrderList.get(position);

        Order_id = myOrders.getId();
        holder.OrderName_txt.setText(myOrders.getRestaurant().getName());
        holder.OrderNp_txt.setText(String.valueOf(myOrders.getId()));
        holder.Total_txt.setText(myOrders.getTotal());
        holder.Delivery_txt.setText(myOrders.getDeliveryCost());
        holder.Cost_txt.setText(myOrders.getCost());


        if(myOrders.getRestaurant().getPhotoUrl()!= null)
        {
            Glide.with(_context).load(myOrders.getRestaurant().getPhotoUrl()).apply(new RequestOptions().override(50,50).centerCrop()).into(h.OrderPhoto_img);
        }

        if(Type == 1) {
            // the previous order tab
            holder.Accept_b.setVisibility(View.VISIBLE);
            holder.Reject_b.setVisibility(View.VISIBLE);

            holder.Reject_b.setOnClickListener(this);
            holder.Accept_b.setOnClickListener(this);
        }

    }



    @Override
    public int getItemCount() {
        return MyOrderList.size();
    }

    @Override
    public void onClick(View v) {

        if(v == h.Accept_b)
        {
            Accept();
        }else if( v == h.Reject_b)
        {
            RejectOrder();
        }


    }

    private void RejectOrder() {

        apiServeces.DelcineOrder(Util.GetApiToken(_context),Order_id).enqueue(new Callback<DeclineOrder>() {
            @Override
            public void onResponse(Call<DeclineOrder> call, Response<DeclineOrder> response) {
                Toast.makeText(_context,response.body().getMsg(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<DeclineOrder> call, Throwable t) {

            }
        });

    }

    private void Accept() {

        apiServeces.ConfirmOrder(Util.GetApiToken(_context),Order_id).enqueue(new Callback<ConfirmOrder>() {
            @Override
            public void onResponse(Call<ConfirmOrder> call, Response<ConfirmOrder> response) {
                Toast.makeText(_context,response.body().getMsg(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ConfirmOrder> call, Throwable t) {
            }
        });
    }

    public class holder extends RecyclerView.ViewHolder {


        TextView OrderName_txt,Cost_txt,Delivery_txt,Total_txt,OrderNp_txt;
        Button Accept_b,Reject_b;
        ImageView OrderPhoto_img;

        public holder(View itemView) {

            super(itemView);

            OrderName_txt = (TextView)itemView.findViewById(R.id.Name_MyCurrentOrder_Row_TXT);
            Cost_txt = (TextView)itemView.findViewById(R.id.CostVal_MyCurrentOrder_Row_TXT);
            Delivery_txt = (TextView)itemView.findViewById(R.id.DeliverVal_MyCurrentOrder_Row_TXT);
            Total_txt = (TextView)itemView.findViewById(R.id.TotalVal_MyCurrentOrder_Row_TXT);
            OrderNp_txt = (TextView)itemView.findViewById(R.id.OrderNoValue_MyCurrentOrder_Row_TXT);

            Accept_b = (Button)itemView.findViewById(R.id.AcceptOrder_MyCurrentOrder_Row_TXT);
            Reject_b = (Button)itemView.findViewById(R.id.RejectOrder_MyCurrentOrder_Row_TXT);

            OrderPhoto_img= (ImageView)itemView.findViewById(R.id.FoodItemIcom_MyCurrentOrder_Row_img);


        }
    }

}
