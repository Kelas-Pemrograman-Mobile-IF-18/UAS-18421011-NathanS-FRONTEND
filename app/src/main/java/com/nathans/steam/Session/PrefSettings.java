package com.nathans.steam.Session;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.nathans.steam.HalamanAdmin;
import com.nathans.steam.HalamanMenu;

public class PrefSettings {

    public static String _id;
    public static String userName;
    public static String email;
    public static String password;
    public static String role;
    Activity activity;

    public PrefSettings(Activity activity){
        this.activity = activity;
    }

    public SharedPreferences getSharePreferences(){
        SharedPreferences preferences = activity.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        return preferences;
    }

    public void isSignin(SessionManager session, SharedPreferences pref){
        session = new SessionManager(activity);
        if (session.isLoggedIn()){
            pref = getSharePreferences();
            _id = pref.getString("_id", "");
            userName = pref.getString("userName", "");
            email = pref.getString("email", "");
            password = pref.getString("password", "");
            role = pref.getString("role", "");
        } else {
            session.setLogin(false);
            session.setSessid(0);
            Intent i = new Intent(activity, activity.getClass());
            activity.startActivity(i);
            activity.finish();
        }
    }

    public void CheckLogin(SessionManager session, SharedPreferences pref){
        session = new SessionManager(activity);
        _id = pref.getString("_id", "");
        userName = pref.getString("userName", "");
        email = pref.getString("email", "");
        password = pref.getString("password", "");
        role = pref.getString("role", "");
        if (session.isLoggedIn()){
            if (role.equals("1")){
                Intent i = new Intent(activity, HalamanAdmin.class);
                activity.startActivity(i);
                activity.finish();
            } else {
                Intent i = new Intent(activity, HalamanMenu.class);
                activity.startActivity(i);
                activity.finish();
            }
        }
    }

    public void storeRegIdSharedPreferences(
            Context context,
            String _id,
            String userName,
            String email,
            String password,
            String role,
            SharedPreferences prefs
    ){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("_id", _id);
        editor.putString("userName", userName);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("role", role);
        editor.commit();
    }
}
