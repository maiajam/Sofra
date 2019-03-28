package com.maiajam.sofa.ui.activites;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maiajam.sofa.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ConnectUsFragm extends android.support.v4.app.Fragment {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabItem)
    TabItem tabItem;
    @BindView(R.id.tabItem2)
    TabItem tabItem2;
    @BindView(R.id.tabItem3)
    TabItem tabItem3;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.ConnectUs_viewPager)
    ViewPager ConnectUsViewPager;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_connectus, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
