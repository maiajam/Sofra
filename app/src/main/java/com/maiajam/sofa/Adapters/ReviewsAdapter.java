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

import java.util.List;


import com.maiajam.sofa.data.models.restReview.RestReviwesData;


public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.holder> {

    Context context ;
    List<RestReviwesData> ReviewsList ;
    String ReviewDate;


    holder h ;


    public ReviewsAdapter(Context context , List<RestReviwesData> list)
    {
        ReviewsList =  list;
        this.context = context ;
    }



    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_row,null);
        h = new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {


        RestReviwesData reviewsObject = ReviewsList.get(position);

        holder.Reviews_txt.setText(reviewsObject.getComment());
        holder.Name_txt.setText(reviewsObject.getClient().getName());

        ReviewDate = reviewsObject.getCreatedAt();


        holder.Date_txt.setText(ReviewDate);
        int Rate = Integer.parseInt(reviewsObject.getRate());
        if(Rate == 1)
        {
            holder.RateStar1_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
        }else if (Rate == 2)
        {
            holder.RateStar1_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar2_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
        }else if(Rate == 3)
        {
            holder.RateStar1_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar2_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar3_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
        }else if(Rate == 4)
        {
            holder.RateStar1_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar2_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar3_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar4_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));


        }else if(Rate == 5)
        {
            holder.RateStar1_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar2_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar3_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar4_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));
            holder.RateStar5_img.setImageDrawable(context.getResources().getDrawable(R.drawable.star_y));

        }








    }



    @Override
    public int getItemCount() {
        return ReviewsList.size();
    }


    public class holder extends RecyclerView.ViewHolder {


        TextView Name_txt,Date_txt,Reviews_txt;
        ImageView RateStar1_img,RateStar2_img,RateStar3_img,RateStar4_img,RateStar5_img;
        public holder(View itemView) {

            super(itemView);

            Name_txt = (TextView) itemView.findViewById(R.id.UserName_Reviews_Row_TXT);
            Date_txt = (TextView) itemView.findViewById(R.id.ReviewsDate_ReviewsItem_Row_TXT);
            Reviews_txt = (TextView) itemView.findViewById(R.id.ReviewsTxt_Reviews_Row_TXT);

            RateStar1_img = (ImageView)itemView.findViewById(R.id.Star1_ReviewsRow_img);
            RateStar2_img = (ImageView)itemView.findViewById(R.id.Star2_ReviewsRow_img);
            RateStar3_img = (ImageView)itemView.findViewById(R.id.Star3_ReviewsRow_img);
            RateStar4_img = (ImageView)itemView.findViewById(R.id.Star4_ReviewsRow_img);
            RateStar5_img = (ImageView)itemView.findViewById(R.id.Star5_ReviewsRow_img);


        }
    }

}

