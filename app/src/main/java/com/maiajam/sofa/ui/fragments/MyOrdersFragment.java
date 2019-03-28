package com.maiajam.sofa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.maiajam.sofa.Adapters.MyOrderAdapter;
import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.myOrders.Datum;
import com.maiajam.sofa.data.models.myOrders.MyOrders;
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

/**
 * Created by maiAjam on 5/7/2018.
 */

public class MyOrdersFragment extends Fragment {

    List<Datum> _ListOfOrders;
    RecyclerView _CurrentOrder_Rec;
    MyOrderAdapter adapter;
    ApiServeces apiServeces;

    @BindView(R.id.MyCurrentOrder_REc)
    RecyclerView MyCurrentOrderREc;

    Unbinder unbinder;
    private int page;

    Bundle extra;
    private int Type;

    public void MyOrdersFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiServeces = getClient().create(ApiServeces.class);

        extra = getArguments();
        if (extra != null) {
            Type = extra.getInt("Type");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mycurrentorders, container, false);
        _CurrentOrder_Rec = (RecyclerView) view.findViewById(R.id.MyCurrentOrder_REc);
        if (Util.CheckConnection(getContext())) {
            if (Type == 1) {
                getCurrentOrders();
            } else {
                getPreviousOrders();
            }
        } else {

        }

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    private void getPreviousOrders() {

        String Api = Util.GetApiToken(getContext());
        apiServeces.getMyOrders(Util.GetApiToken(getContext()), "completed", 1).enqueue(new Callback<MyOrders>() {
            @Override
            public void onResponse(Call<MyOrders> call, Response<MyOrders> response) {

                _ListOfOrders = response.body().getData().getData();
                adapter = new MyOrderAdapter(getContext(), _ListOfOrders, Type);
                _CurrentOrder_Rec.setAdapter(adapter);
                _CurrentOrder_Rec.setLayoutManager(new LinearLayoutManager(getContext()));

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<MyOrders> call, Throwable t) {

            }
        });

    }

    private void getCurrentOrders() {


        String Api = Util.GetApiToken(getContext());

        apiServeces.getMyOrders(Util.GetApiToken(getContext()), "current", page).enqueue(new Callback<MyOrders>() {
            @Override
            public void onResponse(Call<MyOrders> call, Response<MyOrders> response) {
                if (response.body().getStatus() == 1) {
                    _ListOfOrders = response.body().getData().getData();
                    // 1 for current order tab
                    adapter = new MyOrderAdapter(getContext(), _ListOfOrders, 1);
                    _CurrentOrder_Rec.setAdapter(adapter);
                    _CurrentOrder_Rec.setLayoutManager(new LinearLayoutManager(getContext()));

                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MyOrders> call, Throwable t) {

                Log.d("Onfailer: ", t.getMessage().toString());

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

