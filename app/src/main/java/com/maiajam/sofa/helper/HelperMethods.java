package com.maiajam.sofa.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.maiajam.sofa.ui.fragments.FooitemsFragment;

public class HelperMethods {


    public static void beginTransaction(FragmentTransaction fragmentTransaction, Fragment fragment, int frameId, Bundle bundle) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.replace(frameId, fragment);
        fragmentTransaction.commit();
    }

    public static void SetApiToken(Context context,String apiToken,int Type)
    {
        if(Type == 1)
        {
            //Client account
            SharedPreferences sharedPreferences = context.getSharedPreferences("Sofra", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("com.mai.sofra.ApiToken", apiToken);
            editor.commit();
        }else
        {
            // rest owner account
            SharedPreferences sharedPreferences = context.getSharedPreferences("Sofra_rest", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("com.mai.sofra.ApiToken", apiToken);
            editor.commit();
        }

    }


//    public static void get
}
