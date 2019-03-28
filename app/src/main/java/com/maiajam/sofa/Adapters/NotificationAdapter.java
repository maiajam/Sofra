package com.maiajam.sofa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.notifcation.Datum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.holder>  {

    Context context ;
    List<Datum> NotificationList ;
   Datum NotificationObj;
    holder h ;
    String Name;
    String date;
    int Id;

    public NotificationAdapter(Context context , List<Datum> list)
    {

        this.context = context ;
        this.NotificationList = list ;

    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_row,null);
        h = new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        NotificationObj = NotificationList.get(position);


        Name = NotificationObj.getContent();
        date = NotificationObj.getCreatedAt();
        Id = NotificationObj.getId();

        h.Date_txt.setText(new SimpleDateFormat("EEE dd/MM/yyyy",Locale.getDefault()).format(date));
        h.Name_txt.setText(Name);




    }



    @Override
    public int getItemCount() {
        return NotificationList.size();
    }




    public class holder extends RecyclerView.ViewHolder {


        TextView Name_txt,Date_txt;

        public holder(View itemView) {

            super(itemView);

            Name_txt = (TextView)itemView.findViewById(R.id.NotifcatinName_Notification_Row_TXT);
            Date_txt= (TextView)itemView.findViewById(R.id.NotificationDate_Notifcation_Row_TXT);

        }
    }

}
