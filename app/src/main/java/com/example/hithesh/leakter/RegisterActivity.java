package com.example.hithesh.leakter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    public Button reg,b;
    public EditText nam,em,pwd,cnum,ven,vph,mob,m1,m2;
    public dbhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=new dbhelper(this);
        reg=(Button)findViewById(R.id.register);
        b=(Button)findViewById(R.id.back);
        nam=(EditText)findViewById(R.id.name);
        em=(EditText)findViewById(R.id.emid);
        pwd=(EditText)findViewById(R.id.pwd);
        cnum=(EditText)findViewById(R.id.cnum);
        ven=(EditText)findViewById(R.id.vend);
        vph=(EditText)findViewById(R.id.Vnum);
        mob=(EditText)findViewById(R.id.ph);
        m1=(EditText)findViewById(R.id.n1);
        m2=(EditText)findViewById(R.id.n2);
        reg.setOnClickListener(this);
        b.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.register:
                register();
                break;
            case R.id.back:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
            default:
        }
    }
    private void register(){
        String name=nam.getText().toString();
        String email=em.getText().toString();
        String pass=pwd.getText().toString();
        String cn=cnum.getText().toString();
        String vend=ven.getText().toString();
        String vphone=vph.getText().toString();
        String mo=mob.getText().toString();
        String e1=m1.getText().toString();
        String e2=m2.getText().toString();
        if(name.isEmpty()||email.isEmpty()||pass.isEmpty()||cn.isEmpty()||vend.isEmpty()||vphone.isEmpty()||mo.isEmpty()||e1.isEmpty()||e2.isEmpty()){
            displayToast("All fields are mandatory!");
        }
        else
        {
            db.addUser(name,email,pass,cn,vend,vphone,mo,e1,e2);
            displayToast("User registered!");
            finish();
        }
    }
    private void displayToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

    }
}
