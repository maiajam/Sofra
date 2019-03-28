package com.maiajam.sofa.ui.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.Transition;
import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.RestData.Data;
import com.maiajam.sofa.data.models.RestData.RestDetials;
import com.maiajam.sofa.data.models.RestData.Category;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.ui.fragments.FooitemsFragment;
import com.maiajam.sofa.ui.fragments.RestInfoFragment;
import com.maiajam.sofa.ui.fragments.ReviewsFragment;
import com.maiajam.sofa.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.maiajam.sofa.data.restApi.retrofit.ApiServer.getClient;

public class RestFoodItemsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String _RestName;
    int _Rate, _MinCharger, _DeliveryCost, _Stutus, _RestId;
    Data _RestDetials;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.Stuts_RestFoodItems_TXT)
    TextView StutsRestFoodItemsTXT;
    @BindView(R.id.Status_RestFoodItems_Img)
    ImageView StatusRestFoodItemsImg;
    @BindView(R.id.MinChargeTxt_RestFoodItems_TXT)
    TextView MinChargeTxtRestFoodItemsTXT;
    @BindView(R.id.MinCharge_RestFoodItems_TXT)
    TextView MinChargeRestFoodItemsTXT;
    @BindView(R.id.DeleiveryCostTxt_RestFoodItems_TXT)
    TextView DeleiveryCostTxtRestFoodItemsTXT;
    @BindView(R.id.DeleiveryCost_RestFoodItems_TXT)
    TextView DeleiveryCostRestFoodItemsTXT;
    @BindView(R.id.RestuName_RestFoodItems_TXT)
    TextView RestuNameRestFoodItemsTXT;
    @BindView(R.id.FoodComp_RestFoodItems_TXT)
    TextView FoodCompRestFoodItemsTXT;
    @BindView(R.id.Star5_AskFoodRow_img)
    ImageView Star5AskFoodRowImg;
    @BindView(R.id.Star4_AskFoodRow_img)
    ImageView Star4AskFoodRowImg;
    @BindView(R.id.Star3_AskFoodRow_img)
    ImageView Star3AskFoodRowImg;
    @BindView(R.id.Star2_AskFoodRow_img)
    ImageView Star2AskFoodRowImg;
    @BindView(R.id.Star1_AskFoodRow_img)
    ImageView Star1AskFoodRowImg;
    @BindView(R.id.RestuIcom_AskFoodRow_img)
    ImageView RestuIcomAskFoodRowImg;
    @BindView(R.id.RestInfo_RestFoodItems_cardview)
    CardView RestInfoRestFoodItemsCardview;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.ContactNot_viewPager)
    ViewPager ViewPager;
    @BindView(R.id.nav_RestfoodItems)
    NavigationView navRestfoodItems;
    @BindView(R.id.drawer_layout_RestFood)
    DrawerLayout drawerLayoutRestFood;

    private ApiServeces apiServeces;

    List<Category> category_List;
    Category categoryX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_food_items);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            _RestId = extra.getInt("Rest_Id");
        }

        apiServeces = getClient().create(ApiServeces.class);

        if (Util.CheckConnection(getBaseContext())) {
            getRestDetials(_RestId);
        } else {

        }

        pageAdapter adapter = new pageAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FooitemsFragment(), "قائمة الطعام");
        adapter.AddFragment(new ReviewsFragment(), "التعليقات والتقييم");
        adapter.AddFragment(new RestInfoFragment(), "معلومات المتجر");

        ViewPager.setAdapter(adapter);
        tabs.setupWithViewPager(ViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_RestFood);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_RestfoodItems);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_RestFood);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rest_food_items, menu);
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

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_newoffers) {

        } else if (id == R.id.nav_Mynotifications) {

        } else if (id == R.id.nav_myOrders) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_terms) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_RestFood);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    private void getRestDetials(int restId) {

        _RestDetials = new Data();
        apiServeces.getRestDeta(_RestId).enqueue(new Callback<RestDetials>() {
            @Override
            public void onResponse(Call<RestDetials> call, Response<RestDetials> response) {
                _RestDetials = response.body().getData();
                setInfo(_RestDetials);
            }

            @Override
            public void onFailure(Call<RestDetials> call, Throwable t) {

            }
        });
    }

    private void setInfo(Data restDetials) {

        RestuNameRestFoodItemsTXT.setText(restDetials.getName());
        MinChargeRestFoodItemsTXT.setText(restDetials.getMinimumCharger());
        DeleiveryCostRestFoodItemsTXT.setText(restDetials.getDeliveryCost());

        Glide.with(getBaseContext()).load(restDetials.getPhotoUrl()).apply(new RequestOptions().override(40, 40).centerCrop()).into(RestuIcomAskFoodRowImg);

        category_List = new ArrayList<>();
        category_List = restDetials.getCategories();
        categoryX = category_List.get(0);
        String name = categoryX.getName();
        for (int i = 1; i < category_List.size(); i++) {
            name = name + ", " + category_List.get(i).getName();
        }
        //   FoodCompRestFoodItemsTXT.setText(name);


        if (restDetials.getAvailability().equals("open")) {

            StatusRestFoodItemsImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_open));
            StutsRestFoodItemsTXT.setText(getResources().getString(R.string.opennow));
        }


        switch (restDetials.getRate()) {

            case 1:
                Star1AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
            case 2:
                Star1AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
                Star2AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
            case 3:
                Star1AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
                Star2AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
                Star3AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
            case 4:
                Star1AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
                Star2AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
                Star3AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
                Star4AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
            case 5:
                Star1AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
                Star2AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
                Star3AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
                Star4AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));
                Star5AskFoodRowImg.setImageDrawable(getResources().getDrawable(R.drawable.star_y));


        }


    }


    class pageAdapter extends FragmentPagerAdapter {


        List<Fragment> fragmentList = new ArrayList<>();
        List<String> fragmentListTitle = new ArrayList<>();

        public pageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Fragment f = new Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("Rest_Id", _RestId);
                    f = fragmentList.get(position);
                    f.setArguments(bundle);
                    return f;
                case 1:
                    Fragment f2 = new Fragment();
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("Rest_Id", _RestId);
                    f2 = fragmentList.get(position);
                    f2.setArguments(bundle2);
                    return f2;
                case 2:
                    Fragment f3 = new Fragment();
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("Rest_Id", _RestId);
                    f3 = fragmentList.get(position);
                    f3.setArguments(bundle3);
                    return f3;

            }

            return null;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void AddFragment(Fragment fragment, String FragmentTitle) {
            fragmentList.add(fragment);
            fragmentListTitle.add(FragmentTitle);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return fragmentListTitle.get(position);
        }
    }

}
