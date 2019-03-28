package com.maiajam.sofa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.maiajam.sofa.Adapters.NotificationAdapter;
import com.maiajam.sofa.R;

import java.util.ArrayList;
import java.util.List;

import com.maiajam.sofa.Adapters.ListRestAdapter;
import com.maiajam.sofa.data.models.cityList.CityList;
import com.maiajam.sofa.data.models.cityList.Data;
import com.maiajam.sofa.data.models.cityList.Datum;
import com.maiajam.sofa.data.models.notifcation.NotifcationList;
import com.maiajam.sofa.data.models.restList.RestData;
import com.maiajam.sofa.data.models.restList.RestList;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.util.Util;

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

public class AskFoodFragment extends Fragment {

    RecyclerView _AskFooe_rec;
    RecyclerView.LayoutManager _layoutManager;
    ListRestAdapter _Adapter;
    List<RestData> _ListOfRest;
    List<Datum> _ListOfCities;
    List<String> _CityList;
    Data _CitiesData;
    private ApiServeces apiServeces;
    SearchView searchView;
    Spinner _spinner;

    ArrayAdapter _adpater_Spinner;


    public void AskFoodFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_ask_food, container, false);

        apiServeces = getClient().create(ApiServeces.class);
        _AskFooe_rec = (RecyclerView) view.findViewById(R.id.AskFood_Rec);
        _spinner = (Spinner) view.findViewById(R.id.City_AskFood_Spinner);
        _spinner.setPrompt("اختر مدينة");
        _layoutManager = new LinearLayoutManager(getActivity());

        if (Util.CheckConnection(getActivity())) {
//            getFoodItems();
            test(1);
            getCity();
        } else {

        }
        _spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                getRestListByRegion(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        return view;
    }


    @Override
    public void onResume() {

        Log.e("DEBUG", "onResume of LoginFragment");
        if (_spinner.getSelectedItem() != null) {
            Toast.makeText(getContext(), _spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            getRestListByRegion(_spinner.getSelectedItemId());


        }
        super.onResume();
    }

    private void getRestListByRegion(long selectedItemId) {

        apiServeces.getRestListByRegion((int) selectedItemId).enqueue(new Callback<RestList>() {
            @Override
            public void onResponse(Call<RestList> call, Response<RestList> response) {

                _ListOfRest = response.body().getData().getData();
                _Adapter = new ListRestAdapter(getActivity(), _ListOfRest);
                _AskFooe_rec.setAdapter(_Adapter);
                _AskFooe_rec.setLayoutManager(_layoutManager);
                _Adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<RestList> call, Throwable t) {

            }
        });

    }


    private void getCity() {

        apiServeces.getCitytList().enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {

                _ListOfCities = response.body().getData().getData();

                String name = null;
                _CityList = new ArrayList<>();
                _CityList.add(0, "اختر مدينة");
                for (int i = 1; i < _ListOfCities.size(); i++) {
                    name = _ListOfCities.get(i).getName();
                    _CityList.add(name);

                }

                _adpater_Spinner = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, _CityList);


                _spinner.setAdapter(_adpater_Spinner);

            }

            @Override
            public void onFailure(Call<CityList> call, Throwable t) {

            }
        });
    }

    private void test(int page) {

        _ListOfRest = new ArrayList<>();

        apiServeces.getRestList(page).enqueue(new Callback<RestList>() {
            @Override
            public void onResponse(Call<RestList> call, retrofit2.Response<RestList> response) {
                _ListOfRest = response.body().getData().getData();

                _Adapter = new ListRestAdapter(getActivity(), _ListOfRest);

                _AskFooe_rec.setAdapter(_Adapter);
                _AskFooe_rec.setLayoutManager(_layoutManager);

                _Adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<RestList> call, Throwable t) {

            }
        });
    }


}