package com.maiajam.sofa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.RestData.RestDetials;
import com.maiajam.sofa.data.restApi.MyRequestQueue;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.util.MyUrl;
import com.maiajam.sofa.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;

import static com.maiajam.sofa.data.restApi.retrofit.ApiServer.getClient;

/**
 * Created by maiAjam on 5/7/2018.
 */

public class RestInfoFragment extends Fragment {


    TextView _Stuts_txt, _City_txt, _AlHae_txt, _RequestTime_txt, _DeliveryTime_txt, _DeliveryMethode_txt, _MinCharge_txt, _Deliverycost_txt;
    @BindView(R.id.Stuts_RestInfo_Txt)
    TextView StutsRestInfoTxt;
    @BindView(R.id.StutsValue_RestInfo_Txt)
    TextView StutsValueRestInfoTxt;
    @BindView(R.id.City_RestInfo_Txt)
    TextView CityRestInfoTxt;
    @BindView(R.id.CityValue_RestInfo_Txt)
    TextView CityValueRestInfoTxt;
    @BindView(R.id.AlHae_RestInfo_Txt)
    TextView AlHaeRestInfoTxt;
    @BindView(R.id.AlHaeValue_RestInfo_Txt)
    TextView AlHaeValueRestInfoTxt;
    @BindView(R.id.MinCharge_RestInfo_Txt)
    TextView MinChargeRestInfoTxt;
    @BindView(R.id.MinChargeValue_RestInfo_Txt)
    TextView MinChargeValueRestInfoTxt;
    @BindView(R.id.DeliveryCost_RestInfo_Txt)
    TextView DeliveryCostRestInfoTxt;
    @BindView(R.id.DeliveryCostValue_RestInfo_Txt)
    TextView DeliveryCostValueRestInfoTxt;
    @BindView(R.id.constraintLayout)
    LinearLayout constraintLayout;
    Unbinder unbinder;

    ApiServeces apiServeces ;
    private int _RestId;

    public void RestInfoFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiServeces = getClient().create(ApiServeces.class);
        Bundle bundle = getArguments();
        if(bundle!= null)
        {
            _RestId = bundle.getInt("Rest_Id");
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restinfo, container, false);

        _Stuts_txt = (TextView) view.findViewById(R.id.StutsValue_RestInfo_Txt);
        _City_txt = (TextView) view.findViewById(R.id.CityValue_RestInfo_Txt);
        _AlHae_txt = (TextView) view.findViewById(R.id.AlHaeValue_RestInfo_Txt);
        _MinCharge_txt = (TextView) view.findViewById(R.id.MinChargeValue_RestInfo_Txt);
        _Deliverycost_txt = (TextView) view.findViewById(R.id.DeliveryCostValue_RestInfo_Txt);

        unbinder = ButterKnife.bind(this, view);

        if (Util.CheckConnection(getContext())) {
            getRestInfo();
        } else {

        }
        return view;
    }

    private void getRestInfo() {

        apiServeces.getRestDeta(_RestId).enqueue(new Callback<RestDetials>() {
            @Override
            public void onResponse(Call<RestDetials> call, retrofit2.Response<RestDetials> response) {

                String stuts = response.body().getData().getAvailability();
                if(stuts == "open")
                {
                    _Stuts_txt.setText("مفتوح الان");
                    _Stuts_txt.setTextColor(getContext().getResources().getColor(R.color.colorButton));
                }else
                {
                    _Stuts_txt.setText("مغلق");
                    _Stuts_txt.setTextColor(getContext().getResources().getColor(R.color.Red));
                }

                _City_txt.setText(response.body().getData().getRegion().getName());
                _AlHae_txt.setText(response.body().getData().getRegion().getCity().getName().toString());
               // _DeliveryTime_txt.setText(response.body().getData().get);
                _Deliverycost_txt.setText(response.body().getData().getDeliveryCost());
              //  _DeliveryMethode_txt.setText(response.body().getData().);
                _MinCharge_txt.setText(response.body().getData().getMinimumCharger());
            //    _RequestTime_txt.setText(response.body().getData().);
            }

            @Override
            public void onFailure(Call<RestDetials> call, Throwable t) {

            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

