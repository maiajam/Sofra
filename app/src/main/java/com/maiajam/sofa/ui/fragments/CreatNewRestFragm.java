package com.maiajam.sofa.ui.fragments;

import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maiajam.sofa.Adapters.CatagortySpinnerAdapter;
import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.CategorySpinner;
import com.maiajam.sofa.data.models.catagory.CatagoryList;
import com.maiajam.sofa.data.models.catagory.Datum;
import com.maiajam.sofa.data.models.registerRestOwner.RegisterRestOwner;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.helper.HelperMethods;
import com.maiajam.sofa.ui.activites.RestFoodItemsActivity;
import com.maiajam.sofa.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.maiajam.sofa.data.restApi.retrofit.ApiServer.getClient;

public class CreatNewRestFragm extends Fragment
      {


    List<Datum> _CatagoryList;
    String RestName, Email, Pass, PassConfirm,PhoneNo,WhattsNo,Photo_Url,MinCharge,DeliveryCost;
    int Region_Id;

    ApiServeces apiServeces;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.Catagory_NewRest_Sp)
    Spinner CatagoryNewRestSp;
    @BindView(R.id.MinCharge_NewRest_Ed)
    EditText MinChargeNewRestEd;
    @BindView(R.id.DeleiveryCost_NewRest_Ed)
    EditText DeleiveryCostNewRestEd;
    @BindView(R.id.ContactInfo_Txt)
    TextView ContactInfoTxt;
    @BindView(R.id.WhatsApp_Ed)
    EditText WhatsAppEd;
    @BindView(R.id.Phone_Ed_Ed)
    EditText PhoneEdEd;
    @BindView(R.id.RestPhoto_Img)
    ImageView RestPhotoImg;
    @BindView(R.id.photo_Txt)
    TextView photoTxt;
    @BindView(R.id.Register_B)
    Button RegisterB;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    Unbinder unbinder;

    List<CategorySpinner> _List ;
          private String Availabilie;

          @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiServeces = getClient().create(ApiServeces.class);

        Bundle extra = getArguments();
        if (extra != null) {
            RestName = extra.getString("Name");
            Email = extra.getString("Email");
            Region_Id = extra.getInt("Region_Id", 0);
            Pass = extra.getString("Pass");
            PassConfirm = extra.getString("PassConfirm");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_creat_new_rest, container, false);

        if (Util.CheckConnection(getContext())) {
            getCatagory();
        }
        else
        {
             Toast.makeText(getContext(),getContext().getResources().getString(R.string.NoInternet),Toast.LENGTH_LONG).show();
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.RestPhoto_Img, R.id.Register_B})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.RestPhoto_Img:
                SelectImage();
                break;
            case R.id.Register_B:
                RegisterTheRest();
                break;
        }
    }

    private void SelectImage() {

    }

    private void RegisterTheRest() {


        ArrayList<String> SelectedCatagory[] = new ArrayList[_List.size()];
        for (int i = 0; i < _List.size(); i++) {
            if(_List.get(i).isSelected())
            {
                SelectedCatagory[i].add(_List.get(i).getTitle());
            }
        }

        MinCharge = MinChargeNewRestEd.getText().toString();
        DeliveryCost = DeleiveryCostNewRestEd.getText().toString();
        PhoneNo = PhoneEdEd.getText().toString();
        WhattsNo = WhatsAppEd.getText().toString();
        if(!TextUtils.isEmpty(MinCharge)){

            if(SelectedCatagory.length != 0){

                if(!TextUtils.isEmpty(DeliveryCost))
                {
                   if(!TextUtils.isEmpty(PhoneNo))
                   {
                       if(!TextUtils.isEmpty(WhattsNo))
                       {
                           if(!TextUtils.isEmpty(Photo_Url))
                           {
                                if(Util.CheckConnection(getContext()))
                                {
                                    apiServeces.RegisterRestOwner(RestName,Email,Pass,PassConfirm,PhoneNo,WhattsNo,Region_Id,SelectedCatagory,DeliveryCost,MinCharge,Photo_Url,Availabilie).enqueue(new Callback<RegisterRestOwner>() {
                                        @Override
                                        public void onResponse(Call<RegisterRestOwner> call, Response<RegisterRestOwner> response) {

                                            HelperMethods.SetApiToken(getContext(),response.body().getData().getApiToken(),1);
                                            getContext().startActivity(new Intent(getActivity(),RestFoodItemsActivity.class));
                                        }

                                        @Override
                                        public void onFailure(Call<RegisterRestOwner> call, Throwable t) {

                                        }
                                    });

                                }else {
                                    Toast.makeText(getContext(),getResources().getString(R.string.NoInternet),Toast.LENGTH_LONG).show();
                                }
                           }else
                           {
                               Toast.makeText(getContext(),getResources().getString(R.string.PhotoSelect),Toast.LENGTH_LONG).show();
                           }
                       }else {
                           Toast.makeText(getContext(),getResources().getString(R.string.WhatsEnter),Toast.LENGTH_LONG).show();
                       }

                   }else {
                       Toast.makeText(getContext(),getResources().getString(R.string.PhoneEnter),Toast.LENGTH_LONG).show();
                   }
                }else {
                    Toast.makeText(getContext(),getResources().getString(R.string.DeliverCostEnter),Toast.LENGTH_LONG).show();
                }
            }else
            {
                Toast.makeText(getContext(),getResources().getString(R.string.SelectCatagory),Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getContext(),getResources().getString(R.string.MinCHergEnter),Toast.LENGTH_LONG).show();
        }


    }

    private void getCatagory() {
        _CatagoryList = new ArrayList<>();
        apiServeces.getCatagoriList().enqueue(new Callback<CatagoryList>() {
            @Override
            public void onResponse(Call<CatagoryList> call, Response<CatagoryList> response) {
                _CatagoryList = response.body().getData();

                CategorySpinner name = null;
               _List = new ArrayList<>();
                for (int i = 0; i < _CatagoryList.size(); i++) {
                    name.setTitle(_CatagoryList.get(i).getName());
                    name.setSelected(false);
                    _List.add(name);
                }
                CatagortySpinnerAdapter spinnerAdapter = new CatagortySpinnerAdapter(getContext(),R.layout.spinner_item,_List);
             //   ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, _List);
                CatagoryNewRestSp.setAdapter(spinnerAdapter);
                spinnerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<CatagoryList> call, Throwable t) {
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
