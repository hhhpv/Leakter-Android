package com.example.hithesh.leakter;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public Button sin;
    public Button reg;
    public EditText em;
    public EditText pw,n;
    public dbhelper db;
    public session sess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db=new dbhelper(this);
        sess=new session(this);
        sin=(Button)findViewById(R.id.login);
        reg=(Button)findViewById(R.id.register);
        n=(EditText)findViewById(R.id.nams);
        em=(EditText)findViewById(R.id.emid);
        pw=(EditText)findViewById(R.id.pwd);
        sin.setOnClickListener(this);
        reg.setOnClickListener(this);

        if(sess.loggedin()){
            //startActivity(new Intent(LoginActivity.this,MainActivity.class));
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                login();
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            default:
        }

    }
    public void login(){
        String name=n.getText().toString();
        String email=em.getText().toString();
        String password=pw.getText().toString();
        if(name.isEmpty()||email.isEmpty()||password.isEmpty()){
            startActivity(new Intent(LoginActivity.this,LoginActivity.class));
        }
        if(db.getUser(name,email,password)){
            String id=db.getid(name,email,password);
            sess.setLoggedin(true,id);
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Wrong entries!",Toast.LENGTH_SHORT).show();
        }
    }
}
