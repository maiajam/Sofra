package com.maiajam.sofa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maiajam.sofa.R;

import java.util.ArrayList;
import java.util.List;

import com.maiajam.sofa.data.models.MyOffers;
import com.maiajam.sofa.data.models.myoffers.Datum;

public class MyOffersAdapter extends RecyclerView.Adapter<MyOffersAdapter.holder> {

    Context context ;
    List<Datum> MyOfferList ;

    public MyOffersAdapter(Context context, List<Datum> listOfMyOffers)
    {
        this.context = context ;
        this.MyOfferList = listOfMyOffers ;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem_ask_food_row,null);
        return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        


    }



    @Override
    public int getItemCount() {
        return MyOfferList.size();
    }


    public class holder extends RecyclerView.ViewHolder {


        public holder(View itemView) {
            super(itemView);
        }
    }

}
