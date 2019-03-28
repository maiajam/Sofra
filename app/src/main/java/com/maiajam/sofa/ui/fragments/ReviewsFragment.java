package com.maiajam.sofa.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.maiajam.sofa.Adapters.ReviewsAdapter;
import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.restReview.Data;
import com.maiajam.sofa.data.models.restReview.RestReviwes;
import com.maiajam.sofa.data.models.restReview.RestReviwesData;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
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

/**
 * Created by maiAjam on 5/7/2018.
 */

public class ReviewsFragment extends Fragment implements View.OnClickListener {


    List<RestReviwesData> _ListOfReviews= new ArrayList<>();;
    Button _AddReview_b;
    ApiServeces apiServeces;
    int _Rest_Id;
    @BindView(R.id.CommetReview_TXT)
    TextView CommetReviewTXT;
    @BindView(R.id.AddReview_reviews_Row)
    Button AddReviewReviewsRow;
    @BindView(R.id.Reviews_Rec)
    RecyclerView ReviewsRec;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    Unbinder unbinder;
    private String _api_token;
    private int page;
    RecyclerView _Review_Rec;
    ReviewsAdapter _adapter;


    public void ReviewsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiServeces = getClient().create(ApiServeces.class);
        if (Util.CheckConnection(getContext())) {


            getReviewsItems();

        } else {
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        _Review_Rec = (RecyclerView) view.findViewById(R.id.Reviews_Rec);
        _AddReview_b = (Button) view.findViewById(R.id.AddReview_reviews_Row);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onClick(View v) {

        if (v == _AddReview_b) {
            AddReview();
        }
    }

    private void AddReview() {


    }


    private void getReviewsItems() {


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Sofra",Context.MODE_PRIVATE);
        _api_token = sharedPreferences.getString("com.mai.sofra.ApiToken","");
_ListOfReviews = new ArrayList<>();
        apiServeces.getRestReviews(_Rest_Id, _api_token, page).enqueue(new Callback<RestReviwes>() {
            @Override
            public void onResponse(Call<RestReviwes> call, Response<RestReviwes> response) {

                _ListOfReviews = response.body().getData().getData();
                _adapter = new ReviewsAdapter(getContext(), _ListOfReviews);
                _Review_Rec.setAdapter(_adapter);
                _Review_Rec.setLayoutManager(new LinearLayoutManager(getContext()));
                _adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RestReviwes> call, Throwable t) {

            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.CommetReview_TXT, R.id.AddReview_reviews_Row})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.CommetReview_TXT:
                break;
            case R.id.AddReview_reviews_Row:
                break;
        }
    }
}

