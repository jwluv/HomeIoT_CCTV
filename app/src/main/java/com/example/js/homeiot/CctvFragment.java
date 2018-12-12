package com.example.js.homeiot;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;


public class CctvFragment extends Fragment implements View.OnClickListener{


    final float POS_CENTER = 7.5f;
    final float POS_STEP = 0.5f;
    final float POS_MIN = 2.5f;
    final float POS_MAX = 12.5f;

    float posX = POS_CENTER;
    float posY = POS_CENTER;

    WebView webViewCctv, webViewCctvControl;

    Button buttonCenter, buttonUp, buttonRight, buttonDown, buttonLeft;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cctv, container, false);

        webViewCctv = v.findViewById(R.id.webViewCctv);
        webViewCctvControl = v.findViewById(R.id.webViewCctvControl);

        buttonCenter = v.findViewById(R.id.buttonCenter);
        buttonUp = v.findViewById(R.id.buttonUp);
        buttonRight = v.findViewById(R.id.buttonRight);
        buttonDown = v.findViewById(R.id.buttonDown);
        buttonLeft = v.findViewById(R.id.buttonLeft);

        buttonCenter.setOnClickListener(this);
        buttonUp.setOnClickListener(this);
        buttonRight.setOnClickListener(this);
        buttonDown.setOnClickListener(this);
        buttonLeft.setOnClickListener(this);

        webViewCctv.getSettings().setJavaScriptEnabled(true);
        webViewCctv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("HomeIoT_Setup", Context.MODE_PRIVATE);
        String server_ip = sharedPreferences.getString("serverIpKey", null);
        String port = sharedPreferences.getString("CctvPortKey", null);

        if(server_ip != null && port != null) {

            String address = "http://" + server_ip + ":" + port;

            webViewCctv.loadUrl(address);
            webViewCctv.requestFocus();
        }
        else
            Toast.makeText(this.getActivity(), "Input the server IP in Setup", Toast.LENGTH_SHORT).show();

        webViewCctvControl.getSettings().setJavaScriptEnabled(true);
        webViewCctvControl.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webViewCctvControl.loadUrl("http://" + server_ip + ":8200");
        webViewCctvControl.requestFocus();


        return v;
    }

    @Override
    public void onClick(View v) {

        if(v == buttonCenter){
            posX = POS_CENTER;
            posY = POS_CENTER;

            webViewCctvControl.loadUrl("javascript:buttonCenter()");
        }
        else if(v == buttonUp) {
            Toast.makeText(this.getActivity(), "UP", Toast.LENGTH_SHORT).show();

            posY -= POS_STEP;

            if(posY < POS_MIN) {
                posY = POS_MIN;
                Toast.makeText(this.getActivity(), "Out of range", Toast.LENGTH_SHORT).show();
            }

            String str = "javascript:buttonUpDown(" + posY + ")";
            webViewCctvControl.loadUrl(str);
        }
        else if(v == buttonDown) {
            Toast.makeText(this.getActivity(), "DOWN", Toast.LENGTH_SHORT).show();
            posY += POS_STEP;

            if(posY > POS_MAX) {
                posY = POS_MAX;
                Toast.makeText(this.getActivity(), "Out of range", Toast.LENGTH_SHORT).show();
            }

            String str = "javascript:buttonUpDown(" + posY + ")";
            webViewCctvControl.loadUrl(str);
        }
        else if(v == buttonRight) {
            Toast.makeText(this.getActivity(), "RIGHT", Toast.LENGTH_SHORT).show();
            posX -= POS_STEP;

            if(posX < POS_MIN) {
                posX = POS_MIN;
                Toast.makeText(this.getActivity(), "Out of range", Toast.LENGTH_SHORT).show();
            }

            String str = "javascript:buttonRightLeft(" + posX + ")";
            webViewCctvControl.loadUrl(str);
        }
        else if(v == buttonLeft) {
            Toast.makeText(this.getActivity(), "LEFT", Toast.LENGTH_SHORT).show();
            posX += POS_STEP;

            if(posX > POS_MAX) {
                posX = POS_MAX;
                Toast.makeText(this.getActivity(), "Out of range", Toast.LENGTH_SHORT).show();
            }

            String str = "javascript:buttonRightLeft(" + posX + ")";
            webViewCctvControl.loadUrl(str);
        }

    }
}