package com.maiajam.sofa.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.RestFoodItems.FoodItemsData;
import com.maiajam.sofa.data.models.restItems.Datum;
import com.maiajam.sofa.ui.activites.ViewItemDetials;

import java.util.ArrayList;
import java.util.List;

import com.maiajam.sofa.data.models.FoodItem;


public class FoodItemsAdapter extends RecyclerView.Adapter<FoodItemsAdapter.holder> implements View.OnClickListener {

    Context context ;
    List<FoodItemsData> FoodItemList ;
    FoodItemsData FoodItem_object;
    String _FoodItemDesc;
    int _Id;
    String _FoodItemName,_stutus,_ImageURl;


    String _Cost;
    String _PreparingTime;

    holder h ;

    public FoodItemsAdapter(Context context, List<FoodItemsData> _restList)
    {
        this.context = context ;
        this.FoodItemList = _restList ;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem_restfooditems_row,parent,false);
        h = new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        FoodItem_object = FoodItemList.get(position);

        final String FoodItemName,stutus,FoodItemDesc;
        final int Id;

        final String Cost;
        final String PreparingTime;

        FoodItemName = FoodItem_object.getName();
        Id = FoodItem_object.getId();
        FoodItemDesc = FoodItem_object.getDescription();
       Cost = FoodItem_object.getPrice();
       PreparingTime = FoodItem_object.getPreparingTime();

       _FoodItemDesc = FoodItemDesc ;
       _Id = Id ;


        Glide.with(context).load(FoodItem_object.getPhotoUrl()).apply(new RequestOptions().override(50,50).centerCrop()).into(h.FoodItemPhoto_img);
        _ImageURl = FoodItem_object.getPhotoUrl();
       holder.FoodItemDesc_txt.setText(FoodItemDesc);

       holder.FoodItemName_txt.setText(FoodItemName);
       holder.FoodItemCost_txt.setText(String.valueOf(Cost));

       holder.FoodItemInfo_CardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               context.startActivity(new Intent(context,ViewItemDetials.class).putExtra("Name",FoodItemName).
                       putExtra("Desc",FoodItemDesc)
                       .putExtra("Cost",Cost)
                       .putExtra("PreparingTime",PreparingTime)
                       .putExtra("Rest_Id",Id)
                       .putExtra("photo",_ImageURl));

           }
       });

    }



    @Override
    public int getItemCount() {
        return FoodItemList.size();
    }

    @Override
    public void onClick(View v) {

        if(v == h.FoodItemInfo_CardView)
        {



        }

    }


    public class holder extends RecyclerView.ViewHolder {


        TextView FoodItemName_txt,FoodItemDesc_txt,FoodItemCost_txt;
        ImageView FoodItemPhoto_img ;
        CardView FoodItemInfo_CardView ;

        public holder(View itemView) {
            super(itemView);

            FoodItemName_txt = (TextView)itemView.findViewById(R.id.FoodItemName_RestFoodItem_Row_TXT);
            FoodItemCost_txt = (TextView)itemView.findViewById(R.id.Cost_RestFoodItem_Row_TXT);
            FoodItemDesc_txt = (TextView)itemView.findViewById(R.id.FoodDesc_RestFoodItem_Row_TXT);


            FoodItemPhoto_img = (ImageView)itemView.findViewById(R.id.FoodItemIcom_RestFoodItem_Row_img);
            FoodItemInfo_CardView =(CardView)itemView.findViewById(R.id.FoodItemInfo_RestFoodItem_Row_cardview);



        }
    }

}
