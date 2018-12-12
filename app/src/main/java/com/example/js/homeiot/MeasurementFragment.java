package com.example.js.homeiot;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MeasurementFragment extends Fragment implements View.OnClickListener{

    WebView webViewMeasurement;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_measurement, container, false);

        webViewMeasurement = v.findViewById(R.id.webViewMeasurement);

        webViewMeasurement.getSettings().setJavaScriptEnabled(true);
        webViewMeasurement.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("HomeIoT_Setup", Context.MODE_PRIVATE);
        String server_ip = sharedPreferences.getString("serverIpKey", null);
        String port = sharedPreferences.getString("Dht11PortKey", null);

        if(server_ip != null && port != null) {

            String address = "http://" + server_ip + ":" + port;

            webViewMeasurement.loadUrl(address);
            webViewMeasurement.requestFocus();
        }
        else
            Toast.makeText(this.getActivity(), "Input the server IP in Setup", Toast.LENGTH_SHORT).show();
        return v;

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this.getActivity(), "Saved", Toast.LENGTH_SHORT).show();
    }
}
