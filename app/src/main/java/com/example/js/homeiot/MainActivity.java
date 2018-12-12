package com.example.js.homeiot;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DrawerLayout drawerLayout;
//    NavigationView navigationView;
    Toolbar toolbar;
    ImageView toolbar_cctv_db;

    LinearLayout buttonHome, buttonCctv, buttonSetup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar_cctv_db = toolbar.findViewById(R.id.toolbar_cctv_db);
        toolbar_cctv_db.setOnClickListener(this);

        buttonHome = findViewById(R.id.buttonHome);
        buttonCctv = findViewById(R.id.buttonCctv);
        buttonSetup = findViewById(R.id.buttonSetup);

        buttonHome.setOnClickListener(this);
        buttonCctv.setOnClickListener(this);
        buttonSetup.setOnClickListener(this);

        if(checkSetupExist())
            getFragmentManager().beginTransaction().replace(R.id.main_frame, new MeasurementFragment()).commit();
        else
            getFragmentManager().beginTransaction().replace(R.id.main_frame, new SetupFragment()).commit();
    }

    @Override
    public void onClick(View v) {

        if(v == toolbar_cctv_db){

        }
        else if(v == buttonHome) {
            getFragmentManager().beginTransaction().replace(R.id.main_frame, new MeasurementFragment()).commit();
        }
        else if(v == buttonCctv) {
            getFragmentManager().beginTransaction().replace(R.id.main_frame, new CctvFragment()).commit();
        }
        else if(v == buttonSetup) {
            getFragmentManager().beginTransaction().replace(R.id.main_frame, new SetupFragment()).commit();
        }

    }

    public boolean checkSetupExist(){

        SharedPreferences sharedPreferences = this.getSharedPreferences("HomeIoT_Setup", Context.MODE_PRIVATE);
        String server_ip = sharedPreferences.getString("serverIpKey", null);
        String port = sharedPreferences.getString("Dht11PortKey", null);

        if(server_ip == null || port == null)
            return false;
        else
            return true;
    }
}
