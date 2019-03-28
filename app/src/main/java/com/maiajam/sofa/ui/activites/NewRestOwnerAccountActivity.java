package com.maiajam.sofa.ui.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.cityList.CityList;
import com.maiajam.sofa.data.models.cityList.Datum;
import com.maiajam.sofa.data.models.register.Data;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.helper.HelperMethods;
import com.maiajam.sofa.ui.fragments.CreatNewRestFragm;
import com.maiajam.sofa.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewRestOwnerAccountActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar_newRestOwnerAccount)
    Toolbar toolbarNewRestOwnerAccount;
    @BindView(R.id.Name_NewRestOwnerAccount_Ed)
    EditText NameNewRestOwnerAccountEd;
    @BindView(R.id.Email_NewRestOwnerAccount_Ed)
    EditText EmailNewRestOwnerAccountEd;
    @BindView(R.id.City_NewRestOwnerAccount_Sp)
    Spinner CityNewRestOwnerAccountSp;
    @BindView(R.id.Region_NewRestOwnerAccount_Sp)
    Spinner RegionNewRestOwnerAccountSp;
    @BindView(R.id.Password_NewRestOwnerAccount_Ed)
    EditText PasswordNewRestOwnerAccountEd;
    @BindView(R.id.PasswordAgain_NewRestOwnerAccount_Ed)
    EditText PasswordAgainNewRestOwnerAccountEd;
    @BindView(R.id.Next_NewRestOwnerAccount_B)
    Button NextNewRestOwnerAccountB;
    @BindView(R.id.nav_newRestOwnerAccount)
    NavigationView navNewRestOwnerAccount;
    @BindView(R.id.drawer_layout_newRestOwnerAccount)
    DrawerLayout drawerLayoutNewRestOwnerAccount;
    private ArrayList<Datum> _ListOfCities;

    ApiServeces apiServeces ;
    private ArrayList<String> _CityList;
    private ArrayAdapter _adpater_Spinner;
    private int _City_Id;
    private List<com.maiajam.sofa.data.models.region.Datum> _ListOfRegions;
    private ArrayList<String> _RegionList;
    private int _Pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_rest_owner_account);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayoutNewRestOwnerAccount, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayoutNewRestOwnerAccount.addDrawerListener(toggle);
        toggle.syncState();

        navNewRestOwnerAccount.setNavigationItemSelectedListener(this);


        if(Util.CheckConnection(getBaseContext()))
        {

            getCityList();
            getRegionList();
        }else
        {

        }

    }


    @Override
    public void onBackPressed() {

        if (drawerLayoutNewRestOwnerAccount.isDrawerOpen(GravityCompat.END)) {
            drawerLayoutNewRestOwnerAccount.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_rest_owner_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawerLayoutNewRestOwnerAccount.closeDrawer(GravityCompat.END);
        return true;
    }

    @OnClick(R.id.Next_NewRestOwnerAccount_B)
    public void onViewClicked() {

        String Name = NameNewRestOwnerAccountEd.getText().toString();
        String Email = EmailNewRestOwnerAccountEd.getText().toString();

        int Region_Id = _ListOfRegions.get(RegionNewRestOwnerAccountSp.getSelectedItemPosition()).getId();

        String Pass = PasswordNewRestOwnerAccountEd.getText().toString();
        String AgainPass = PasswordAgainNewRestOwnerAccountEd.getText().toString();

        if(!TextUtils.isEmpty(Name))
        {
                if(!TextUtils.isEmpty(Email))
                {
                        if(Region_Id != 0)
                        {

                                if(!TextUtils.isEmpty(Pass))
                                {

                                    if(!TextUtils.isEmpty(AgainPass))
                                    {

                                        Fragment f = new CreatNewRestFragm();
                                        Bundle bundle = new Bundle();
                                       bundle.putString("Name",Name);
                                        bundle.putString("Email",Email);
                                        bundle.putInt("Region_Id",Region_Id);
                                        bundle.putString("Pass",Pass);
                                        bundle.putString("PassConfirm",AgainPass);
                                        HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(),f,R.id.frame,bundle);

                                    }else
                                    {

                                    }

                                }else
                                {

                                }
                            }else
                            {

                            }
                        }else
                         {

                         }
                    }else
                         {

                         }




    }



    private void getCityList() {


        _ListOfCities = new ArrayList<>();

        apiServeces.getCitytList().enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {

                _ListOfCities = (ArrayList<Datum>) response.body().getData().getData();

                String name = null ;
                _CityList = new ArrayList<>();
                for (int i = 0;i< _ListOfCities.size();i++)
                {
                    name = _ListOfCities.get(i).getName();
                    _CityList.add(name);

                }

                _adpater_Spinner =  new ArrayAdapter(getBaseContext(),R.layout.support_simple_spinner_dropdown_item, _CityList);

                CityNewRestOwnerAccountSp.setAdapter(_adpater_Spinner);

                _adpater_Spinner.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CityList> call, Throwable t) {

            }
        });
    }

    private void getRegionList() {

        _ListOfRegions = new ArrayList<>();
        _City_Id = _ListOfCities.get(_Pos).getId();


    /*    apiServeces.getRegionList(_City_Id).enqueue(new Callback<com.maiajam.sofa.data.models.region.Data>() {
            @Override
            public void onResponse(Call<com.maiajam.sofa.data.models.region.Data> call, Response<com.maiajam.sofa.data.models.region.Data> response) {

                _ListOfRegions = response.body().getData();

                String name = null ;
                _RegionList = new ArrayList<>();
                for (int i = 0;i< _ListOfRegions.size();i++)
                {
                    name = _ListOfRegions.get(i).getName();
                    _RegionList.add(name);

                }

                _adpater_Spinner =  new ArrayAdapter(getBaseContext(),R.layout.support_simple_spinner_dropdown_item, _RegionList);

               RegionNewRestOwnerAccountSp.setAdapter(_adpater_Spinner);
                _adpater_Spinner.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<com.maiajam.sofa.data.models.region.Data> call, Throwable t) {

            }
        });
*/
    }
}
