package com.maiajam.sofa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.myOrders.Datum;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyOrderAndOffersFragm extends Fragment {


    Unbinder unbinder;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.myorder_viewPager)
    ViewPager myorderViewPager;

    @Nullable
    @BindView(R.id.tabprevious)
    TabItem tabprevious;

    @Nullable
    @BindView(R.id.tabCurrent)
    TabItem tabCurrent;
    private ApiServeces apiServeces;

    List<Datum> _ListOfOrders;

    public void MyOrderAndOffersFragm() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.content_myorder, container, false);
        unbinder = ButterKnife.bind(this, view);
        pageAdapter adapter = new pageAdapter(getFragmentManager());
        adapter.AddFragment(new MyOrdersFragment(), "طلباتي الحالية");
        adapter.AddFragment(new MyOrdersFragment(), "طلباتي السابقة");

        myorderViewPager.setAdapter(adapter);
        tabs.setupWithViewPager(myorderViewPager);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public class pageAdapter extends FragmentPagerAdapter {

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
                    bundle.putInt("Type", 1);
                    f = fragmentList.get(position);
                    f.setArguments(bundle);
                    return f;
                case 1:
                    Fragment f2 = new Fragment();
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("Type", 0);
                    f2 = fragmentList.get(position);
                    f2.setArguments(bundle2);
                    return f2;
            }

            return null;
        }

        @Override
        public int getCount() {
            return fragmentListTitle.size();
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
