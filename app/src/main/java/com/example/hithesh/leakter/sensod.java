package com.example.hithesh.leakter;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.util.HashMap;

public class sensod extends AppCompatActivity {
    private static Button retbt;
    public String url = "https://api.thingspeak.com/channels/{channel_number}/fields/1/last?key={thingspeak_api_key}";
    public int l;
    public TextView dispn;
    public dbhelper db;
    public session s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensod);
        dispn = (TextView) findViewById(R.id.dnam);
        db = new dbhelper(this);
        s = new session(getApplicationContext());// <-- this is what you missed
        HashMap<String, String> user = s.getUserDetails();
        String name = user.get(session.n);
        String dname = "Hello, " + db.getname(name);
        dispn.setText(dname);
        retr();
    }

    public void retr() {
        retbt = (Button) findViewById(R.id.button);
        final TextView mTextView = (TextView) findViewById(R.id.textView3);
        final RequestQueue queue = Volley.newRequestQueue(this);

        retbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                l = response.length();
                                double rf;
                                rf = Double.parseDouble(response);
                                rf = (rf / 1023.0) * 100.0;
                                mTextView.setText("Response is: " + rf + " %" + "(A-Value=" + response + ")");
                                // Log.i("VOLLEY",response);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mTextView.setText("That didn't work! Try again..check internet connection.");
                                // Log.i("VOLLEY","error");
                            }

                        });
                queue.add(stringRequest);
            }
        });
    }

}