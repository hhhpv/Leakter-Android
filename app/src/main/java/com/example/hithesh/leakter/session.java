package com.example.hithesh.leakter;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Hithesh on 13-02-2018.
 */

public class session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
    public static String n;
    public static String e;
    public static String p;
    public session(Context ctx)
    {
        this.ctx=ctx;
        prefs=ctx.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        editor=prefs.edit();
    }
    public void setLoggedin(boolean loggedin,String name){
        editor.putBoolean("loggedInmode",loggedin);
        editor.putString(n,name);
        editor.commit();
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(n,prefs.getString(n,n));
        return user;
    }
    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode",false);
    }
}
