package com.maiajam.sofa.ui.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.login.Login;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;
import com.maiajam.sofa.helper.HelperMethods;
import com.maiajam.sofa.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.maiajam.sofa.data.restApi.retrofit.ApiServer.getClient;

public class LoginFragment extends android.support.v4.app.Fragment {

    String _Email, _Password;
    ApiServeces apiServeces;
    @BindView(R.id.Password_Login_Ed)
    EditText PasswordLoginEd;
    @BindView(R.id.Email_Login_Ed)
    EditText EmailLoginEd;
    @BindView(R.id.Login_Login_B)
    Button LoginLoginB;
    @BindView(R.id.ForgetPass_Login_Txt)
    TextView ForgetPassLoginTxt;
    @BindView(R.id.CreatNewAccount_Login_B)
    Button CreatNewAccountLoginB;
    Unbinder unbinder;
    private int Type;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiServeces = getClient().create(ApiServeces.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(R.layout.activity_login,container,false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.Login_Login_B, R.id.CreatNewAccount_Login_B})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Login_Login_B:
                if(Util.CheckConnection(getContext()))
                {
                    LoginNow();
                }else
                {
                  Toast.makeText(getContext(),getContext().getResources().getString(R.string.NoInternet),Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.CreatNewAccount_Login_B:
                HelperMethods.beginTransaction(getFragmentManager().beginTransaction(),new CreatNewAccountFragm(),R.id.frame,null);
                break;
        }
    }

    private void LoginNow() {

        _Email = EmailLoginEd.getText().toString();
        _Password = PasswordLoginEd.getText().toString();

        if (!TextUtils.isEmpty(_Email)) {
            if (!TextUtils.isEmpty(_Password)) {
                login(_Email, _Password);
            } else {
                Toast.makeText(getContext(),getContext().getResources().getString(R.string.ENterPass),Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getContext(),getContext().getResources().getString(R.string.ENterEmail),Toast.LENGTH_LONG).show();
        }
    }


    private void login(String email, String password) {

        apiServeces.Login(email, password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if (response.body().getStatus() == 1){
                    String msg = response.message().toString();
                    String apiToken = response.body().getData().getApiToken();
                    HelperMethods.SetApiToken(getContext(),apiToken,1);
                    getActivity().finish();
                }else {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });

    }

}


