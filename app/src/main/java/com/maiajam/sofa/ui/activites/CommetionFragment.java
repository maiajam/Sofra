package com.maiajam.sofa.ui.activites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.commission.Commission;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.maiajam.sofa.data.restApi.retrofit.ApiServer.getClient;

public class CommetionFragment extends Fragment {


    @BindView(R.id.RestSaleVal_Commtion_Txt)
    TextView RestSaleValCommtionTxt;
    @BindView(R.id.AppCommtionVal_Commtion_Txt)
    TextView AppCommtionValCommtionTxt;
    @BindView(R.id.PayingdeptVal_Commtion_Txt)
    TextView PayingdeptValCommtionTxt;
    @BindView(R.id.ReminingVal_Commtion_Txt)
    TextView ReminingValCommtionTxt;
    @BindView(R.id.RestSale_Commtion_Txt)
    TextView RestSaleCommtionTxt;
    @BindView(R.id.AppCommtion_Commtion_Txt)
    TextView AppCommtionCommtionTxt;
    @BindView(R.id.Payingdept_Commtion_Txt)
    TextView PayingdeptCommtionTxt;
    @BindView(R.id.Remining_Commtion_Txt)
    TextView ReminingCommtionTxt;
    Unbinder unbinder;

    ApiServeces apiServeces ;


   public CommetionFragment()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    apiServeces = getClient().create(ApiServeces.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.content_commtion, container, false);
        unbinder = ButterKnife.bind(this, view);


        if (Util.CheckConnection(getContext())) {
            getCommtion();
        } else {
            Toast.makeText(getContext(),getResources().getString(R.string.NoInternet),Toast.LENGTH_LONG).show();
        }
        return view;
    }

    private void getCommtion() {

        apiServeces.getCommssion(Util.GetApiToken(getContext())).enqueue(new Callback<Commission>() {
            @Override
            public void onResponse(Call<Commission> call, Response<Commission> response) {

                if(response.body().getStatus()== 1)
                {
                    RestSaleValCommtionTxt.setText(response.body().getData().getTotal());
                    AppCommtionValCommtionTxt.setText(response.body().getData().getCommissions());
                    PayingdeptValCommtionTxt.setText(response.body().getData().getPayments());
                    ReminingValCommtionTxt.setText(response.body().getData().getCount());
                }else {
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Commission> call, Throwable t) {

            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
