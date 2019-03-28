package com.maiajam.sofa.ui.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.maiajam.sofa.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView _TwiteerIcon_img,_InstIcon_img;
    Button _Sale_b,_Buy_b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        _Sale_b =(Button)findViewById(R.id.Sale_welcome_B);
        _Buy_b=(Button)findViewById(R.id.Aِsk_welcome_B);

        _TwiteerIcon_img =(ImageView) findViewById(R.id.Twitter_welcom_img);
        _InstIcon_img =(ImageView)findViewById(R.id.Instgrame_welcome_img);

        _Sale_b.setOnClickListener(this);
        _Buy_b.setOnClickListener(this);

        _TwiteerIcon_img.setOnClickListener(this);
        _InstIcon_img.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == _Sale_b)
        {
                startActivity(new Intent(MainActivity.this,AskandSaleFoodActivity.class).putExtra("salefood",1));

        }else if(v == _Buy_b)
        {

            startActivity(new Intent(MainActivity.this,AskandSaleFoodActivity.class));

        }else if(v == _InstIcon_img)
        {



        }else if(v == _TwiteerIcon_img)
        {


        }

    }
}
