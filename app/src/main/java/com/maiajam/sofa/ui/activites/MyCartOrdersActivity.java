package com.maiajam.sofa.ui.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.maiajam.sofa.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.maiajam.sofa.Adapters.CartOrdersAdapter;
import com.maiajam.sofa.data.models.CartOrders;
import com.maiajam.sofa.data.restApi.MyRequestQueue;
import com.maiajam.sofa.util.MyUrl;
import com.maiajam.sofa.util.Util;

public class MyCartOrdersActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView _CartOrder_Rec;
    Button _AddMore_b,_DoOrder_b;
    TextView _TotalOrderPrice_txt;
    ArrayList<CartOrders> _Order_list;
    CartOrdersAdapter adapter ;
    RecyclerView.LayoutManager layoutManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart_orders);


        _CartOrder_Rec = (RecyclerView)findViewById(R.id.MyCardOrder_REC);

        _AddMore_b = (Button)findViewById(R.id.AddMore_MyCardOrder_B);
        _DoOrder_b = (Button)findViewById(R.id.DoOrder_MyCardOrder_B);

        _TotalOrderPrice_txt = (TextView)findViewById(R.id.TotalOrderPrice_MyCardOrder_B);


       adapter = new CartOrdersAdapter( getBaseContext(),getCartOrder());
       layoutManager = new LinearLayoutManager(getBaseContext());
       ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);


       _CartOrder_Rec.setLayoutManager(layoutManager);
       _CartOrder_Rec.setAdapter(adapter);

       // set the total order price at the textview




    }

    private ArrayList<CartOrders> getCartOrder() {

        if(Util.CheckConnection(getBaseContext()))
        {

        }else
        {

            _Order_list = new ArrayList<>();
        }

        return _Order_list;
    }

    @Override
    public void onClick(View v) {

        if(v == _AddMore_b)
        {

        }else if(v == _DoOrder_b)
        {
           // startActivity(new Intent(MyCartOrdersActivity.this,LoginActivity.class));
           // finish();
        }

    }
}
