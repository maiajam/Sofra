package com.maiajam.sofa.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.maiajam.sofa.Adapters.MyOffersAdapter;
import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.Offers.Data;
import com.maiajam.sofa.data.models.Offers.Offers;
import com.maiajam.sofa.data.models.myoffers.Datum;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.ui.activites.AddNewOfferActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.maiajam.sofa.Adapters.NewOffersAdapter;
import com.maiajam.sofa.data.models.NewOffers;
import com.maiajam.sofa.data.restApi.MyRequestQueue;
import com.maiajam.sofa.util.MyUrl;
import com.maiajam.sofa.util.Util;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by maiAjam on 5/7/2018.
 */

public class OffersFragment extends Fragment implements View.OnClickListener {


    RecyclerView _NewOffer_Rec;
    NewOffersAdapter adapter ;
    MyOffersAdapter myOffersAdapter;
    List<com.maiajam.sofa.data.models.Offers.Datum> _ListOfNewOffers ;

    ApiServeces apiServeces ;

    Button _AddnewOff_b;
    int FragmentType = 0 ;
    private List<Datum> _ListOfMyOffers;


    public void OffersFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extra = getArguments();
        if(extra!= null)
        {
            FragmentType = extra.getInt("Type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_new_offer,container,false);

        _NewOffer_Rec =(RecyclerView)view.findViewById(R.id.NewOffers_Rec);
        _AddnewOff_b = (Button)view.findViewById(R.id.AddNew_NewOffers_B);

        if(FragmentType == 1)
            _AddnewOff_b.setVisibility(View.VISIBLE);
        else
            _AddnewOff_b.setVisibility(View.GONE);

        if(Util.CheckConnection(getContext()))
        {
            getOffersList();
        }else
        {

        }
        return view ;
    }

    private void getOffersList() {



        if(FragmentType == 1)
        {

           /* apiServeces.getMyOffers(Util.GetApiToken(getContext()),"",1).enqueue(new Callback<com.maiajam.sofa.data.models.myoffers.Data>() {
                @Override
                public void onResponse(Call<com.maiajam.sofa.data.models.myoffers.Data> call, retrofit2.Response<com.maiajam.sofa.data.models.myoffers.Data> response) {

                    _ListOfMyOffers = response.body().getData();
                    myOffersAdapter = new MyOffersAdapter(getContext(),_ListOfMyOffers);
                    _NewOffer_Rec.setAdapter(adapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    _NewOffer_Rec.setLayoutManager(layoutManager);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<com.maiajam.sofa.data.models.myoffers.Data> call, Throwable t) {

                }
            });
            */
        }else
        {
            // new offers

            apiServeces.ListOffOffers().enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, retrofit2.Response<Data> response) {
                    _ListOfNewOffers = response.body().getData();
                    adapter = new NewOffersAdapter(getContext(),_ListOfNewOffers);
                    _NewOffer_Rec.setAdapter(adapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    _NewOffer_Rec.setLayoutManager(layoutManager);
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {

                }
            });
        }


    }




    @Override
    public void onClick(View v) {

        if(v ==  _AddnewOff_b)
        {

            startActivity(new Intent(getActivity(),AddNewOfferActivity.class));
        }
    }
}

