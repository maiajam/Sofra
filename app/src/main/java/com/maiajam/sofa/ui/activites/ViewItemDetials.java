package com.maiajam.sofa.ui.activites;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.addTocart.AddToCart;
import com.maiajam.sofa.data.restApi.retrofit.ApiServeces;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.maiajam.sofa.data.restApi.retrofit.ApiServer.getClient;

public class ViewItemDetials extends AppCompatActivity implements View.OnClickListener {


    ImageView _ItemPhoto_img;
    TextView _ItemName_txt,_ItemDesc_txt,_ItemCost_txt,_PreparingTime_txt;
    EditText _SpecialOrder_ed,_Amount_ed;
    Button _Increase_b,_Decrease_b,_AddToCart_b;

    String Name,Desc,Image_Url;
    int Cost,PrepareTime;

    ApiServeces apiServeces ;
    private String _apiToken;
    int _item_Id,Quantity;
    String note ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_detials);

        apiServeces = getClient().create(ApiServeces.class);
        _ItemName_txt = (TextView)findViewById(R.id.ItemName_ViewItemDetials_TXT);
        _ItemDesc_txt = (TextView)findViewById(R.id.ItemDesc_ViewItemDetials_TXT);
        _ItemCost_txt = (TextView)findViewById(R.id.ItemCoset_ViewItemDetials_TXT);
        _PreparingTime_txt = (TextView)findViewById(R.id.PreparTimeValue_ViewItemDetials_TXT);

        _ItemPhoto_img = (ImageView)findViewById(R.id.ItemPhoto_ViewItemDetials_Img);

        _SpecialOrder_ed = (EditText)findViewById(R.id.SpecailOrder_ViewItemDetials_Ed);
        _Amount_ed = (EditText)findViewById(R.id.AmountValue_ViewItemDetials_Ed);

        _Increase_b = (Button)findViewById(R.id.Increse_ViewItemDetials_B);
        _Decrease_b = (Button)findViewById(R.id.Decrese_ViewItemDetials_B);
        _AddToCart_b = (Button)findViewById(R.id.AddToCart_ViewItemDetials_B);



        Bundle extra = getIntent().getExtras();
        if(extra!= null)
        {
          Name = extra.getString("Name");
          Desc = extra.getString("Desc");
          Cost = extra.getInt("Cost");
          _item_Id = extra.getInt("Id");
          PrepareTime = extra.getInt("PreparingTime");
          Image_Url = extra.getString("photo");

        }

        SharedPreferences sh = getSharedPreferences("Sofra",MODE_PRIVATE);
        _apiToken = sh.getString("com.mai.sofra.ApiToken","");

        setValues();

        _Amount_ed.setText(String.valueOf(1));

        _Increase_b.setOnClickListener(this);
        _Decrease_b.setOnClickListener(this);
        _AddToCart_b.setOnClickListener(this);

        Glide.with(getBaseContext()).load(Image_Url).apply(new RequestOptions().override(50,50).centerCrop()).into(_ItemPhoto_img);


    }

    private void setValues() {

        _ItemName_txt.setText(Name);
        _ItemDesc_txt.setText(Desc);
        _ItemCost_txt.setText(String.valueOf(Cost));
        _PreparingTime_txt.setText(String.valueOf(PrepareTime));

    }

    @Override
    public void onClick(View v) {

        if(v == _Increase_b)
        {
            int amount = Integer.parseInt(_Amount_ed.getText().toString());
            amount ++ ;
            _Amount_ed.setText(String.valueOf(amount));
            Quantity = amount ;

        }else if ( v == _Decrease_b)
        {
            int amount = Integer.parseInt(_Amount_ed.getText().toString());
            if(amount> 1)
            {
                amount -- ;
                Quantity = amount ;
                _Amount_ed.setText(String.valueOf(amount));
            }else
            {
                Toast.makeText(getBaseContext(),"لقد وصلت للحد الادني للكمية",Toast.LENGTH_LONG).show();

            }

        }else if ( v == _AddToCart_b )
        {

            AddToCart();
        }


    }

    private void AddToCart() {

        // add this order to cart by webservice

        apiServeces.AddTOCart(_apiToken,_item_Id,Quantity,note).enqueue(new Callback<AddToCart>() {
            @Override
            public void onResponse(Call<AddToCart> call, Response<AddToCart> response) {

                Toast.makeText(getBaseContext(),response.body().getMsg().toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<AddToCart> call, Throwable t) {

            }
        });



    }
}
