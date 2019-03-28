package com.maiajam.sofa.ui.activites;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.maiajam.sofa.R;

import com.maiajam.sofa.ui.fragments.OffersFragment;

public class MyOffersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offers);


        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
        OffersFragment offersFragment = new OffersFragment();
        Bundle b = new Bundle();
        b.putInt("Type",1);
        offersFragment.setArguments(b);
        ft.replace(R.id.frame_myOffer, new OffersFragment());
        ft.commit();


    }
}
