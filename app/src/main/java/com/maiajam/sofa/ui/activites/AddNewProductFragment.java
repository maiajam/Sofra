package com.maiajam.sofa.ui.activites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maiajam.sofa.R;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.util.Util;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.maiajam.sofa.data.restApi.retrofit.ApiServer.getClient;

public class AddNewProductFragment extends Fragment {

    EditText _ProductName_ed, _ProductDesc_ed, _Cost_ed, _PrepareTime_ed;
    Button _Add_b;
    ImageView _ProductPhoto_img;

    String _ProductName, _ProductDesc;
    int _Cost = 0, _PrepareTime = 0;
    @BindView(R.id.ProductName_addNewProduct_Ed)
    EditText ProductNameAddNewProductEd;
    @BindView(R.id.ProductDesc_addNewProduct_Ed)
    EditText ProductDescAddNewProductEd;
    @BindView(R.id.Cost_addNewProduct_Ed)
    EditText CostAddNewProductEd;
    @BindView(R.id.Time_addNewProduct_Ed)
    EditText TimeAddNewProductEd;
    @BindView(R.id.TimeTo_Ed)
    EditText TimeToEd;
    @BindView(R.id.TimeFrom_Ed)
    EditText TimeFromEd;
    @BindView(R.id.ProductPhoto_addNewProduct_Img)
    ImageView ProductPhotoAddNewProductImg;
    @BindView(R.id.AddPhoto_addNewProduct_TXT)
    TextView AddPhotoAddNewProductTXT;
    @BindView(R.id.Add_addNewProduct_B)
    Button AddAddNewProductB;
    Unbinder unbinder;
    ApiServeces apiServeces ;
    private int Type;
    private String _TimeFrom;
    private String _TimeTo;

    public AddNewProductFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiServeces = getClient().create(ApiServeces.class);
        Bundle bundle = getArguments();
        if(bundle!=null)
        {
            Type = bundle.getInt("type",0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_new_offer_and_product, container, false);
        unbinder = ButterKnife.bind(this, view);

        if(Type == 1)
        { //add new offers
            ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("أضف عرض");
           TimeAddNewProductEd.setVisibility(View.GONE);
           TimeFromEd.setVisibility(View.VISIBLE);
           TimeToEd.setVisibility(View.VISIBLE);
        }else {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("أضف منتج");
        }


        return view;
    }

    private void AddNewProduct(String productName, String productDesc, int cost, int prepareTime, String _TimeFrom, String _TimeTo) {
     //   apiServeces.addNewOffer(productName,productDesc,cost,_TimeFrom,_TimeTo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ProductPhoto_addNewProduct_Img, R.id.Add_addNewProduct_B})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ProductPhoto_addNewProduct_Img:
                break;
            case R.id.Add_addNewProduct_B:
                if(Util.CheckConnection(getActivity()))
                {
                    Add();
                }else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.NoInternet),Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void Add() {

        _ProductName = ProductNameAddNewProductEd.getText().toString();
        _ProductDesc = ProductDescAddNewProductEd.getText().toString();
        _Cost = Integer.parseInt(CostAddNewProductEd.getText().toString());
        if(Type == 1)
        {// add new offer
           _TimeFrom = TimeFromEd.getText().toString();
           _TimeTo = TimeToEd.getText().toString();
        }else { //add new product
            _PrepareTime = Integer.parseInt(TimeAddNewProductEd.getText().toString());
        }

        if (!TextUtils.isEmpty(_ProductName)) {
            AddNewProduct(_ProductName,_ProductDesc,_Cost,_PrepareTime,_TimeFrom,_TimeTo);
        }else {
            return;
        }
        //
        if (!TextUtils.isEmpty(_ProductDesc)) {
            AddNewProduct(_ProductName,_ProductDesc,_Cost,_PrepareTime,_TimeFrom,_TimeTo);
        }else {
            return;
        }
        //
        if (_Cost != 0) {
            AddNewProduct(_ProductName,_ProductDesc,_Cost,_PrepareTime,_TimeFrom,_TimeTo);
        }else {
            return;
        }
        //
        if (_PrepareTime != 0) {
            AddNewProduct(_ProductName,_ProductDesc,_Cost,_PrepareTime,_TimeFrom,_TimeTo);
        }else {
            return;
        }
//
        if(Type == 1)
        {
            if (!TextUtils.isEmpty(_TimeFrom)) {
                AddNewProduct(_ProductName,_ProductDesc,_Cost,_PrepareTime,_TimeFrom,_TimeTo);
            }else {
                return;
            }
            //
            if (!TextUtils.isEmpty(_TimeTo)) {
                AddNewProduct(_ProductName,_ProductDesc,_Cost,_PrepareTime,_TimeFrom,_TimeTo);
            }else {
                return;
            }

        }



    }
}
