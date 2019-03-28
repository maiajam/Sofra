package com.maiajam.sofa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.maiajam.sofa.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by maiAjam on 5/7/2018.
 */

public class NewProductAndOffersFragment extends Fragment implements View.OnClickListener {


    EditText _Name_ed, _Desc_ed, _Cost_ed, _FromTime_ed, _ToTime_ed, _Time_ed;
    ImageView _Photo_Img;
    Button _Add_b;
    int FragmentType = 0;
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


    public void NewProductAndOffersFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extra = getArguments();
        if (extra != null) {
            FragmentType = extra.getInt("Type");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_offer_and_product, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onClick(View v) {

        if (v == _Add_b) {

            _ProductName = _Name_ed.getText().toString();
            _ProductDesc = _Desc_ed.getText().toString();
            _Cost = Integer.parseInt(_Cost_ed.getText().toString());
            _PrepareTime = Integer.parseInt(_Time_ed.getText().toString());

            if (!TextUtils.isEmpty(_ProductName)) {
                if (!TextUtils.isEmpty(_ProductDesc)) {
                    if (_Cost != 0) {
                        if (_PrepareTime != 0) {

                            if (FragmentType == 1) {
                                // from new offer activity

                                AddNewOffer(_ProductName, _ProductDesc, _Cost, _PrepareTime);
                            } else {
                                // from new product activity
                                AddNewProduct(_ProductName, _ProductDesc, _Cost, _PrepareTime);
                            }

                        } else {
                            Toast.makeText(getContext(), "dsfgd", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "dsfgd", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "dsfgd", Toast.LENGTH_LONG).show();
                }
            } else {

            }

        }
    }

    private void AddNewOffer(String productName, String productDesc, int cost, int prepareTime) {

    }

    private void AddNewProduct(String productName, String productDesc, int cost, int prepareTime) {


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Add_addNewProduct_B)
    public void onViewClicked() {


    }
}

