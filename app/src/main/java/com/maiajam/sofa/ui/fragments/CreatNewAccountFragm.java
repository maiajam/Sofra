package com.maiajam.sofa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.cityList.CityList;
import com.maiajam.sofa.data.models.cityList.Datum;
import com.maiajam.sofa.data.models.region.Region;
import com.maiajam.sofa.data.models.register.Register;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.helper.HelperMethods;
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

public class CreatNewAccountFragm extends Fragment {


    int ClientUser;
    String apiToken;

    ApiServeces apiServeces;
    @BindView(R.id.Name_CreatNewAcc_Ed)
    EditText NameCreatNewAccEd;
    @BindView(R.id.Email_CreatNewAcc_Ed)
    EditText EmailCreatNewAccEd;
    @BindView(R.id.Phone_CreatNewAcc_Ed)
    EditText PhoneCreatNewAccEd;
    @BindView(R.id.City_CreatNewAcc_Sp)
    Spinner CityCreatNewAccSp;
    @BindView(R.id.Region_CreatNewAcc_Sp)
    Spinner RegionCreatNewAccSp;
    @BindView(R.id.DescPlace_CreatNewAcc_Ed)
    EditText DescPlaceCreatNewAccEd;
    @BindView(R.id.Password_CreatNewAcc_Ed)
    EditText PasswordCreatNewAccEd;
    @BindView(R.id.PasswordAgain_CreatNewAcc_Ed)
    EditText PasswordAgainCreatNewAccEd;
    @BindView(R.id.Next_CreatNewAcc_B)
    Button NextCreatNewAccB;
    Unbinder unbinder;


    private List<Datum> _ListOfCities;
    private ArrayList<String> _CityList;
    private ArrayAdapter _adpater_Spinner;
    private int _City_Id;
    private List<com.maiajam.sofa.data.models.region.Datum> _ListOfRegions;
    private ArrayList<String> _RegionList;
    private int _Pos;
    int Type;
    private List<Integer> _CityIdList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_creat_new_account, container, false);
        unbinder = ButterKnife.bind(this, view);

        apiServeces = getClient().create(ApiServeces.class);
        Bundle extra = getArguments();
        if (extra != null) {
            ClientUser = extra.getInt("Client", 0);
        }

        if (Util.CheckConnection(getContext())) {
            getCityList();

        } else {
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.NoInternet), Toast.LENGTH_LONG).show();
        }

//        CityCreatNewAccSp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                _Pos = CityCreatNewAccSp.getSelectedItemPosition();
//            }
//        });


        return view;
    }

    @OnClick({R.id.Next_CreatNewAcc_B})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Next_CreatNewAcc_B:
                Register();
                break;
        }
    }

    private void Register() {

        String Name = NameCreatNewAccEd.getText().toString();
        String PhoneNo = PhoneCreatNewAccEd.getText().toString();
        String Email = EmailCreatNewAccEd.getText().toString();
        String City = CityCreatNewAccSp.getSelectedItem().toString();
        String Region = RegionCreatNewAccSp.getSelectedItem().toString();
        int Region_Id = _ListOfRegions.get(RegionCreatNewAccSp.getSelectedItemPosition()).getId();
        String Desc = DescPlaceCreatNewAccEd.getText().toString();
        String Pass = PasswordCreatNewAccEd.getText().toString();
        String AgainPass = PasswordAgainCreatNewAccEd.getText().toString();

        if (TextUtils.isEmpty(Name)) {


            return;

        }
        if (!TextUtils.isEmpty(Name)) {
            if (TextUtils.isEmpty(PhoneNo)) {
                if (TextUtils.isEmpty(Email)) {

                    if (TextUtils.isEmpty(City)) {

                        if (TextUtils.isEmpty(Region)) {
                            if (TextUtils.isEmpty(Desc)) {

                                if (TextUtils.isEmpty(Pass)) {

                                    if (TextUtils.isEmpty(AgainPass)) {
                                        if (Util.CheckConnection(getContext())) {
                                            if (ClientUser == 1) {

                                                apiServeces.Register(Name, Email, Pass, AgainPass, PhoneNo, Desc, Region_Id).enqueue(new Callback<Register>() {
                                                    @Override
                                                    public void onResponse(Call<Register> call, Response<Register> response) {

                                                        if (response.body().getStatus() == 1) {
                                                            apiToken = response.body().getData().getApiToken();
                                                            HelperMethods.SetApiToken(getContext(), apiToken, ClientUser);
                                                        } else {
                                                            Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                        }

                                                    }

                                                    @Override
                                                    public void onFailure(Call<Register> call, Throwable t) {

                                                    }
                                                });

                                            } else {
                                                // restrant owner

                                                // Fragment f = new
                                                // HelperMethods.beginTransaction(getFragmentManager().beginTransaction(),new );
                                            }
                                        } else {
                                            Toast.makeText(getContext(), getContext().getResources().getString(R.string.NoInternet), Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(getContext(), getContext().getResources().getString(R.string.ConfirmPass), Toast.LENGTH_LONG).show();
                                    }

                                } else {
                                    Toast.makeText(getContext(), getContext().getResources().getString(R.string.ENterPass), Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Toast.makeText(getContext(), getContext().getResources().getString(R.string.EnterDesc), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getContext(), getContext().getResources().getString(R.string.ENterRegion), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), getContext().getResources().getString(R.string.ENterCity), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getContext(), getContext().getResources().getString(R.string.ENterEmail), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.EnrPhon), Toast.LENGTH_LONG).show();
        }
    }


    private void getCityList() {

        _ListOfCities = new ArrayList<>();

        apiServeces.getCitytList().enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {

                if (response.body().getStatus() == 1) {
                    _ListOfCities = response.body().getData().getData();

                    String name = null;
                    _CityList = new ArrayList<>();

                    _CityList.add(0, "اختر مدينة");
                    for (int i = 1; i < _ListOfCities.size(); i++) {
                        name = _ListOfCities.get(i).getName();
                        _CityList.add(name);
                        Integer id = _ListOfCities.get(i).getId();
                        _CityIdList.add(id);

                    }

                    _adpater_Spinner = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, _CityList);

                    CityCreatNewAccSp.setAdapter(_adpater_Spinner);
                    CityCreatNewAccSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getRegionList(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<CityList> call, Throwable t) {

                Log.d("onFailure: .", t.getMessage().toString());

            }
        });
    }

    private void getRegionList(int position) {

        if (position != 0) {
            _ListOfRegions = new ArrayList<>();
            _City_Id = _CityIdList.get(position) - 1;


            apiServeces.getRegionList(_City_Id).enqueue(new Callback<Region>() {
                @Override
                public void onResponse(Call<Region> call, Response<Region> response) {
                    if (response.body().getStatus() == 1) {
                        _ListOfRegions = response.body().getData().getData();
                        String name = null;
                        _RegionList = new ArrayList<>();
                        for (int i = 0; i < _ListOfRegions.size(); i++) {
                            name = _ListOfRegions.get(i).getName();
                            _RegionList.add(name);
                        }
                        _adpater_Spinner = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, _RegionList);
                        RegionCreatNewAccSp.setAdapter(_adpater_Spinner);
                        _adpater_Spinner.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Region> call, Throwable t) {

                    Log.d("Failer", t.getMessage().toString());
                }
            });
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
