package com.maiajam.sofa.ui.activites;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maiajam.sofa.R;

import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.helper.HelperMethods;
import com.maiajam.sofa.ui.fragments.AskFoodFragment;
import com.maiajam.sofa.ui.fragments.FooitemsFragment;
import com.maiajam.sofa.ui.fragments.LoginFragment;
import com.maiajam.sofa.ui.fragments.MyOrderAndOffersFragm;
import com.maiajam.sofa.ui.fragments.NotificationFragment;
import com.maiajam.sofa.ui.fragments.OffersFragment;

import java.util.Locale;

import static com.maiajam.sofa.data.restApi.retrofit.ApiServer.getClient;

public class AskandSaleFoodActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int SaleFrag;
    ApiServeces apiServeces ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_food);
        Locale locale = new Locale("ar");
        setRTL(locale);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            SaleFrag = extra.getInt("salefood", 0);
        }

        if (SaleFrag == 0) {
            toolbar.setTitle("طلب طعام");
        } else {
            toolbar.setTitle("بيع طعام");

        }


       apiServeces = getClient().create(ApiServeces.class);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);

        ImageView Setting_view = (ImageView) header.findViewById(R.id.Setting_navigatiom_img);
        Setting_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(), new LoginFragment(), R.id.frame, null);
            }
        });

        TextView Name = (TextView) header.findViewById(R.id.UserName_Nav_TXT);
        Name.setText("");
        navigationView.setNavigationItemSelectedListener(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        FragmentTransaction ft;


        if (SaleFrag == 1) {
            FooitemsFragment fI = new FooitemsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("Sale", 1);
            HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(), fI, R.id.frame, bundle);

            navigationView.getMenu().findItem(R.id.nav_orders).setTitle("منتجاتي");
            navigationView.getMenu().findItem(R.id.nav_newoffers).setTitle("عروضي");
            navigationView.getMenu().findItem(R.id.nav_commsiton).setVisible(true);
            drawer.openDrawer(GravityCompat.START);

        } else {
            HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(), new AskFoodFragment(), R.id.frame, null);
        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ask_food, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
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
            HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(), new AskFoodFragment(), R.id.frame, null);
        } else if (id == R.id.nav_contact) {
           HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(), new ConnectUsFragm(), R.id.frame, null);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_terms) {

        } else if (id == R.id.nav_newoffers) {
           if (SaleFrag == 1)
           {
               HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(), new MyOrderAndOffersFragm(), R.id.frame, null);
           }else
           {
               HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(),new OffersFragment(), R.id.frame, null);
           }
       } else if (id == R.id.nav_about) {
            HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(), new AboutAppFragment(), R.id.frame, null);
        } else if (id == R.id.nav_Mynotifications) {
            HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(), new NotificationFragment(), R.id.frame, null);
        } else if (id == R.id.nav_orders) {
           if (SaleFrag == 1)
           {
               Bundle b = new Bundle();
               b.putInt("Sale",1);
               HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(), new FooitemsFragment(), R.id.frame, b);
           }else {
               HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(), new MyOrderAndOffersFragm(), R.id.frame, null);
           }
        }else if(id == R.id.nav_commsiton)
       {
           HelperMethods.beginTransaction(getSupportFragmentManager().beginTransaction(), new CommetionFragment(), R.id.frame, null);
       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext()
                .getResources().updateConfiguration(config, null);
        // return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
        //       directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }
}
