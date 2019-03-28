package com.maiajam.sofa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.maiajam.sofa.R;

import com.maiajam.sofa.util.Util;

/**
 * Created by maiAjam on 5/7/2018.
 */

public class ConnectUsFragment extends Fragment implements View.OnClickListener {




    EditText _Name_ed,_Email_ed,_PhoneNo_ed,_Content_ed;
    Button _Send_b;
    ImageView _Facebook_img,_Inst_img,_Twitter_img;

    public void ConnectUsFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_connectus,container,false);
        _Name_ed = (EditText)view.findViewById(R.id.Name_ConnectUs_Ed);
        _Email_ed = (EditText)view.findViewById(R.id.Email_ConnectUs_Ed);
        _PhoneNo_ed = (EditText)view.findViewById(R.id.Phone_ConnectUs_Ed);
        _Content_ed = (EditText)view.findViewById(R.id.Content_ConnectUs_Ed);

        _Send_b= (Button)view.findViewById(R.id.Send_ConnectUs_B);

        _Inst_img = (ImageView)view.findViewById(R.id.Instgrame_ConnectUs_img);
        _Twitter_img = (ImageView)view.findViewById(R.id.Twitter_ConnectUs_img);
        _Facebook_img = (ImageView)view.findViewById(R.id.Facebook_ConnectUs_img);


        return view ;
    }


    @Override
    public void onClick(View v) {

        if(v == _Send_b)
        {
            String Name = _Name_ed.getText().toString();
            String Email = _Email_ed.getText().toString();
            String PhoneNO = _PhoneNo_ed.getText().toString();
            String Content = _Content_ed.getText().toString();
            if(!Name.isEmpty())
            {
                if(!Email.isEmpty())
                {
                    if(!PhoneNO.isEmpty())
                    {
                        if(!Content.isEmpty())
                        {
                            if(Util.CheckConnection(getContext()))
                            {

                                    sendTheContent(Email,Name,PhoneNO,Content);

                            }else
                            {
                                Toast.makeText(getContext(),"تأكد من اتصال الانترنت لديك",Toast.LENGTH_LONG).show();
                            }

                        }else
                        {
                            Toast.makeText(getContext()," عذرا النص فارغ",Toast.LENGTH_LONG).show();
                        }
                    }else
                    {
                        Toast.makeText(getContext()," عذرا النص فارغ",Toast.LENGTH_LONG).show();
                    }
                }else
                {
                    Toast.makeText(getContext()," عذرا النص فارغ",Toast.LENGTH_LONG).show();
                }
            }

        }else if(v ==_Inst_img)
        {

        }else if(v == _Facebook_img)
        {

        }else if(v == _Twitter_img)
        {

        }

    }

    private void sendTheContent(String email, String name, String phoneNO, String content) {


    }
}

