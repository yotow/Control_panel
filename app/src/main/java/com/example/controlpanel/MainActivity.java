package com.example.controlpanel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

//    WiFiActivity wiFiActivity = new WiFiActivity();
    public void onClick(View view) {
        TextView tv = findViewById(R.id.textView1Main);
        if(view == findViewById(R.id.button_get_ip)){
  //          String s = wiFiActivity.getDeviceCurrentIPAddress();

            tv.setText("");
        }
    }
}

/*
class WiFiActivity extends MainActivity{

    public String getDeviceCurrentIPAddress() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        assert wifiManager != null;
        int ip = wifiManager.getConnectionInfo().getIpAddress();

        String ipString = String.format("%d.%d.%d.%d", (ip & 0xff),
                (ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
        return ipString;
    }

}*/

