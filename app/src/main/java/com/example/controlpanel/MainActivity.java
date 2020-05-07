package com.example.controlpanel;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    /*  TextView mPingTextView;
      TextView mTextView;*/
    EditText mEditText;
    String mIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.edit_text);
    }

    public void onClick(View view) {
        String url = "http://" + mIp + "/socket1On";
        doRequest(url);

    }

    public void onClickSetIP(View view) {
        mIp = mEditText.getText().toString().trim();

    }

    public void onClickLightOff(View view) {
        String url = "http://" + mIp + "/socket1Off";
        doRequest(url);
    }

    void doRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
/*
    public void doPing(View view) {
        String ip = "192.168.5.1";
        mPingTextView.setText("execute ping");
        Runtime runtime = Runtime.getRuntime();
        try {
            Process mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 " + ip);
            int mExitValue = mIpAddrProcess.waitFor();
            mPingTextView.append("\n mExitValue " + mExitValue);
            if (mExitValue == 0) {
                mPingTextView.append("\nok");
            } else {
                mPingTextView.append("\nwrong");
            }
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
            System.out.println(" Exception:" + ignore);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" Exception:" + e);
        }
    }*/
}