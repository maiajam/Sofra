package com.maiajam.sofa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.maiajam.sofa.Adapters.NotificationAdapter;
import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.notifcation.Datum;
import com.maiajam.sofa.data.models.notifcation.NotifcationList;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.maiajam.sofa.data.restApi.retrofit.ApiServer.getClient;

public class NotificationFragment extends Fragment {

    NotificationAdapter adapter;
    List<Datum> _NotifcationList;

    ApiServeces apiServeces;
    @BindView(R.id.Notiication_REc)
    RecyclerView NotiicationREc;
    @BindView(R.id.constraintLayout)
    RelativeLayout constraintLayout;
    Unbinder unbinder;


    public void NotificationFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiServeces = getClient().create(ApiServeces.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (Util.CheckConnection(getContext())) {
            getNotifcation();
        } else {
            Toast.makeText(getContext(),getResources().getString(R.string.NoInternet),Toast.LENGTH_LONG).show();
        }
        return view;
    }



    private void getNotifcation() {

        apiServeces.getNotifcationList(Util.GetApiToken(getContext())).enqueue(new Callback<NotifcationList>() {
            @Override
            public void onResponse(Call<NotifcationList> call, Response<NotifcationList> response) {

                if(response.body().getStatus()== 1)
                {
                    _NotifcationList = response.body().getData().getData();

                    adapter = new NotificationAdapter(getContext(), _NotifcationList);
                    NotiicationREc.setAdapter(adapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    NotiicationREc.setLayoutManager(layoutManager);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<NotifcationList> call, Throwable t) {
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}


