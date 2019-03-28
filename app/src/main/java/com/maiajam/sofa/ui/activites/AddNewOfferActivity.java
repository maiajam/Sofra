package com.maiajam.sofa.ui.activites;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.maiajam.sofa.R;
import com.maiajam.sofa.ui.fragments.OffersFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewOfferActivity extends AppCompatActivity {

    @BindView(R.id.Frame_AddnewOffer)
    FrameLayout FrameAddnewOffer;
    @BindView(R.id.nav_addnewOffer)
    NavigationView navAddnewOffer;
    @BindView(R.id.drawer_layout_addnewOffer)
    DrawerLayout drawerLayoutAddnewOffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_offer);
        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("أضف عرض");


        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
        OffersFragment offersFragment = new OffersFragment();
        Bundle b = new Bundle();
        b.putInt("Type", 1);
        offersFragment.setArguments(b);
        ft.replace(R.id.Frame_AddnewOffer, new OffersFragment());
        ft.commit();

    }

    @OnClick({R.id.Frame_AddnewOffer, R.id.nav_addnewOffer, R.id.drawer_layout_addnewOffer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Frame_AddnewOffer:
                break;
            case R.id.nav_addnewOffer:
                break;
            case R.id.drawer_layout_addnewOffer:
                break;
        }
    }
}
