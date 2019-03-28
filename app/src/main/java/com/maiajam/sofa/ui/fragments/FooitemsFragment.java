package com.maiajam.sofa.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.maiajam.sofa.Adapters.FoodItemsAdapter;
import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.RestData.RestDetials;
import com.maiajam.sofa.data.models.RestFoodItems.FoodItemsData;
import com.maiajam.sofa.data.models.RestFoodItems.RestFoodItems;
import com.maiajam.sofa.data.models.restItems.Data;
import com.maiajam.sofa.data.models.restItems.Datum;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.maiajam.sofa.data.models.FoodItem;
import com.maiajam.sofa.data.models.MyProducts;
import com.maiajam.sofa.data.restApi.MyRequestQueue;
import com.maiajam.sofa.helper.HelperMethods;
import com.maiajam.sofa.ui.activites.AddNewProductFragment;
import com.maiajam.sofa.util.MyUrl;
import com.maiajam.sofa.util.Util;

import retrofit2.Call;
import retrofit2.Callback;

import static com.maiajam.sofa.data.restApi.retrofit.ApiServer.getClient;

/**
 * Created by maiAjam on 5/7/2018.
 */

public class FooitemsFragment extends Fragment implements View.OnClickListener {


    List<FoodItemsData> _ListOfItems = new ArrayList<>();
    ArrayList<MyProducts> _ListOfProducts ;
    RecyclerView _List_Rec ;
    Button _AddNewProduct;
    public int Type ;
    public int _RestId;

    FoodItemsAdapter adapter ;

    ApiServeces apiServeces ;




    public void FooitemsFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiServeces = getClient().create(ApiServeces.class);
        Bundle extra = getArguments();
        if(extra != null)
        {

            _RestId = extra.getInt("Rest_Id");
            Type = extra.getInt("Sale",0);
        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fooditems,container,false);
        _List_Rec =(RecyclerView)view.findViewById(R.id.FoodItems_Rec);
        _AddNewProduct =(Button)view.findViewById(R.id.AddProduct_FoodItems_Txt);
        _AddNewProduct.setOnClickListener(this);
        if(Type == 1)
        {
            _AddNewProduct.setVisibility(View.VISIBLE);
        }else
        {
            _AddNewProduct.setVisibility(View.GONE);
        }

        if(Util.CheckConnection(getContext()))
        {

            if(Type == 1)
            {
                // عرش شاشة قائمة منتجاتي
                _ListOfProducts =getMyProdect();
            }else
            {
                getFoodItems();
            }


        }else
        {

        }

        return view ;
    }


    @Override
    public void onClick(View v) {
        if(v == _AddNewProduct)
        {
            Bundle b = new Bundle();
            new Bundle().putInt("type",1);
            HelperMethods.beginTransaction(getFragmentManager().beginTransaction(),new AddNewProductFragment(),R.id.frame,b);
        }
    }

    private ArrayList<MyProducts> getMyProdect() {

        return null;

    }

    private void getFoodItems() {

        apiServeces.getRestitems(_RestId).enqueue(new Callback<RestFoodItems>() {
            @Override
            public void onResponse(Call<RestFoodItems> call, retrofit2.Response<RestFoodItems> response) {
              //  Toast.makeText(getContext(),"toast",Toast.LENGTH_LONG).show();
                _ListOfItems = response.body().getData().getData();
                adapter = new FoodItemsAdapter(getContext(),_ListOfItems);
                _List_Rec.setAdapter(adapter);
                _List_Rec.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RestFoodItems> call, Throwable t) {
                Log.d("", "onFailure: "+ t.toString());
            }
        });

    }

}

