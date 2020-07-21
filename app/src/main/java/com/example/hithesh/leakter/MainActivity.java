package com.example.hithesh.leakter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.InputStream;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public session s;
    public Button blogout,senbt,prebt,inf;
    public String name;
    public dbhelper db;
    public TextView dispn;
    public String dname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s=new session(this);
        if(!s.loggedin()){
            logout();
        }
        blogout=(Button)findViewById(R.id.out);
        blogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                logout();
            }
        });
        sen();
    }
    public void logout(){
        s.setLoggedin(false,null);
        finish();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }
    public void sen() {
        senbt = (Button)findViewById(R.id.show);
        prebt = (Button)findViewById(R.id.open);
        dispn=(TextView)findViewById(R.id.dispn);
        inf=(Button)findViewById(R.id.inf);
        db=new dbhelper(this);
        s = new session(getApplicationContext());//<-- this is what you missed
        HashMap<String, String> user = s.getUserDetails();
        String name = user.get(session.n);
        dname="Hello, "+db.getname(name);
        dispn.setText(dname);
        senbt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,sensod.class);
                        startActivity(intent);
                    }
                });

        prebt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,predict.class);
                        startActivity(intent);
                    }
                });
        inf.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent=new Intent(MainActivity.this,infrd.class);
                        startActivity(intent);
                    }
                }
        );


    }
}
