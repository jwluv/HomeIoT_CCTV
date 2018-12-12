package com.example.js.homeiot;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class SetupFragment extends Fragment implements View.OnClickListener{

    EditText editTextSetupServerIp, editTextSetupDht11Port, editTextSetupCctvPort;
    Button buttonSetupSave;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_setup, container, false);

        editTextSetupServerIp = v.findViewById(R.id.editTextSetupServerIp);
        editTextSetupDht11Port = v.findViewById(R.id.editTextSetupDht11Port);
        editTextSetupCctvPort = v.findViewById(R.id.editTextSetupCctvPort);

        buttonSetupSave = v.findViewById(R.id.buttonSetupSave);
        buttonSetupSave.setOnClickListener(this);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("HomeIoT_Setup", Context.MODE_PRIVATE);
        String server_ip = sharedPreferences.getString("serverIpKey", null);
        String port_dht11 = sharedPreferences.getString("Dht11PortKey", null);
        String port_cctv = sharedPreferences.getString("CctvPortKey", null);

        if(server_ip != null) {
            editTextSetupServerIp.setText(server_ip);
            editTextSetupServerIp.clearFocus();
        }
        if(port_dht11 != null) {
            editTextSetupDht11Port.setText(port_dht11);
            editTextSetupDht11Port.clearFocus();
        }
        if(port_cctv != null) {
            editTextSetupCctvPort.setText(port_cctv);
            editTextSetupCctvPort.clearFocus();
        }

        InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        return v;

    }

    @Override
    public void onClick(View v) {
        if(v == buttonSetupSave) {
            SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("HomeIoT_Setup", Context.MODE_PRIVATE).edit();
            editor.putString("serverIpKey", editTextSetupServerIp.getText().toString());
            editor.putString("Dht11PortKey", editTextSetupDht11Port.getText().toString());
            editor.putString("CctvPortKey", editTextSetupCctvPort.getText().toString());
            editor.commit();
        }
    }
}
