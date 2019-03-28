package com.maiajam.sofa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maiajam.sofa.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.maiajam.sofa.data.models.NewOffers;
import com.maiajam.sofa.data.models.Offers.Data;
import com.maiajam.sofa.data.models.Offers.Datum;



public class NewOffersAdapter extends RecyclerView.Adapter<NewOffersAdapter.holder> {

    Context context ;
    List<Datum> NewOffersList ;
    String ReviewDate;
    holder h ;



    public NewOffersAdapter(Context context, List<Datum> listOfOffers) {

        NewOffersList = listOfOffers ;
        this.context = context ;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_offer_row,null);
        h = new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {


        Datum NewOffersObject = NewOffersList.get(position);

        holder.OfferName_txt.setText(NewOffersObject.getName());
        holder.RestName_txt.setText(NewOffersObject.getRestaurant().getName());
        holder.Cost_txt.setText(NewOffersObject.getPrice());

        ReviewDate = NewOffersObject.getCreatedAt();


        holder.Date_txt.setText(new SimpleDateFormat("EEE dd/MM/yyyy",Locale.getDefault()).format(ReviewDate));






    }



    @Override
    public int getItemCount() {
        return NewOffersList.size();
    }


    public class holder extends RecyclerView.ViewHolder {


        TextView OfferName_txt,Date_txt,RestName_txt,Cost_txt;
        ImageView OfferPhoto_img;
        public holder(View itemView) {

            super(itemView);


            Date_txt = (TextView) itemView.findViewById(R.id.Time_NewOffers_Row_TXT);
           OfferName_txt = (TextView) itemView.findViewById(R.id.OfferTitle_NewOffers_Row_TXT);
            RestName_txt = (TextView) itemView.findViewById(R.id.RestNameOffer_NewOffers_Row_TXT);
            Cost_txt = (TextView) itemView.findViewById(R.id.Cost_NewOffers_Row_TXT);


            OfferPhoto_img = (ImageView) itemView.findViewById(R.id.NewOfferPhoto_NewOffer_Row_img);



        }
    }

}

