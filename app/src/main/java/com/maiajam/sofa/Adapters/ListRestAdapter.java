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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.restList.Category;
import com.maiajam.sofa.data.models.restList.RestData;
import com.maiajam.sofa.ui.activites.RestFoodItemsActivity;

import java.util.ArrayList;
import java.util.List;


public class ListRestAdapter extends RecyclerView.Adapter<ListRestAdapter.holder> implements View.OnClickListener {

    Context context;
    List<RestData> RestList;

    String RestName, stutus;
    int Rate, DeliveryDays;
    String MinCharger;
    String DeliveryCost;
    holder h ;
    List<Category> category_List ;
    Category categoryX;
    String Catagory_name;
    List<String> category_NameList;

    public ListRestAdapter(Context context, List<RestData> _restList) {
        this.context = context;
        this.RestList = _restList;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem_ask_food_row, parent,false);
        h = new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {


        RestData RestInfo_object = RestList.get(position);

        RestName = RestInfo_object.getName();
       final int id = RestInfo_object.getId();
        Rate = RestInfo_object.getRate();
        stutus = RestInfo_object.getAvailability();
        MinCharger = RestInfo_object.getMinimumCharger();
        DeliveryCost = RestInfo_object.getDeliveryCost();
        category_List = RestInfo_object.getCategories();
        category_NameList = new ArrayList<>();


        categoryX = category_List.get(0);
        String name = categoryX.getName();

      //  ArrayList<String>
        for (int i = 1; i < category_List.size(); i++)
        {
            name = name +", " +category_List.get(i).getName() ;
        }


        Glide.with(context).load(RestInfo_object.getPhotoUrl()).apply(new RequestOptions().override(50,50).centerCrop()).into(h.RestPhoto_img);

     //   DeliveryDays = RestInfo_object.get;
        Rate = RestInfo_object.getRate();

      holder.FoodComp_TXT.setText(name);

        holder.RestName_txt.setText(RestName);
        holder.MinCharge_txt.setText( MinCharger);
        holder.DeliveryCose_txt.setText( DeliveryCost);

        if (Rate == 1) {

            holder.RateStar1_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));

        } else if (Rate == 2) {
            holder.RateStar1_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar2_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
        } else if (Rate == 3) {

            holder.RateStar1_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar2_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar3_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
        } else if (Rate == 4) {
            holder.RateStar1_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar2_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar3_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar4_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));


        } else if (Rate == 5) {
            holder.RateStar1_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar2_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar3_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar4_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar5_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));


        }


        if (stutus.equals("open")) {

            holder.RestStuts_img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_open));
            holder.Status_txt.setText(context.getResources().getString(R.string.opennow));
        }

        holder.RestInfo_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(context, RestFoodItemsActivity.class);
                i.putExtra("Rest_Id",id);
                context.startActivity(i);



            }
        });
        holder.RestPhoto_img.setOnClickListener(this);


    }


    @Override
    public int getItemCount() {
        return RestList.size();
    }

    @Override
    public void onClick(View v) {

        if (v == h.RestInfo_CardView) {

        }else if(v == h.RestPhoto_img)
        {

            Toast.makeText(context,"test",Toast.LENGTH_LONG).show();
        }else
        {
          //  int id = v.;

        }

    }


    public class holder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView RestName_txt, MinCharge_txt, DeliveryCose_txt, DeliveryDays_txt, Status_txt,FoodComp_TXT;
        ImageView RestPhoto_img, RestStuts_img, RateStar1_img, RateStar2_img, RateStar3_img, RateStar4_img, RateStar5_img;
        CardView RestInfo_CardView;

        public holder(View itemView) {
            super(itemView);

            RestName_txt = (TextView) itemView.findViewById(R.id.RestuName_AskFood_Row_TXT);
            MinCharge_txt = (TextView) itemView.findViewById(R.id.MinChargeTxt_AskFood_Row_TXT);
            DeliveryCose_txt = (TextView) itemView.findViewById(R.id.DeleiveryCostTxt_AskFood_Row_TXT);
            Status_txt = (TextView) itemView.findViewById(R.id.Stuts_AskFoodRow_TXT);
            FoodComp_TXT =(TextView) itemView.findViewById(R.id.FoodComp_AskFood_Row_TXT);

            RestPhoto_img = (ImageView) itemView.findViewById(R.id.RestuIcom_AskFoodRow_img);
            RestStuts_img = (ImageView) itemView.findViewById(R.id.Status_AskFoodRow_Img);
            RateStar1_img = (ImageView) itemView.findViewById(R.id.Star1_AskFoodRow_img);
            RateStar2_img = (ImageView) itemView.findViewById(R.id.Star2_AskFoodRow_img);
            RateStar3_img = (ImageView) itemView.findViewById(R.id.Star3_AskFoodRow_img);
            RateStar4_img = (ImageView) itemView.findViewById(R.id.Star4_AskFoodRow_img);
            RateStar5_img = (ImageView) itemView.findViewById(R.id.Star5_AskFoodRow_img);

            RestInfo_CardView = (CardView) itemView.findViewById(R.id.RestInfo_cardview);



        }

        @Override
        public void onClick(View v) {
            if(v== h.RestPhoto_img)
            {
                Toast.makeText(context,"clciked",Toast.LENGTH_LONG).show();
            }
        }
    }

}
