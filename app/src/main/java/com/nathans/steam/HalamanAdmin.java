package com.nathans.steam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.nathans.steam.Admin.ActivityDataGames;
import com.nathans.steam.Admin.ActivityDataWallet;
import com.nathans.steam.Admin.InputGame;
import com.nathans.steam.Admin.InputWallet;
import com.nathans.steam.Session.PrefSettings;
import com.nathans.steam.Session.SessionManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanAdmin extends AppCompatActivity {

    SessionManager sessions;
    SharedPreferences prefs;
    PrefSettings prefSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_admin);

        ButterKnife.bind(this);

        prefSettings = new PrefSettings(this);
        prefs = prefSettings.getSharePreferences();

        sessions = new SessionManager(HalamanAdmin.this);

        prefSettings.isSignin(sessions, prefs);
    }

    @OnClick(R.id.btnProfile)
    void viewprofile(){
        Intent i = new Intent(HalamanAdmin.this, HalamanProfile.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.dataWallet)
    void viewdatawallet(){
        Intent i = new Intent(HalamanAdmin.this, ActivityDataWallet.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.inputWallet)
    void viewinputwallet(){
        Intent i = new Intent(HalamanAdmin.this, InputWallet.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.dataGame)
    void viewdatagame(){
        Intent i = new Intent(HalamanAdmin.this, ActivityDataGames.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.inputGame)
    void viewinputgame(){
        Intent i = new Intent(HalamanAdmin.this, InputGame.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnLogout)
    public void logout() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.steamlogo2)
                .setTitle("Admin Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessions.setLogin(false);
                        sessions.setSessid(0);
                        Intent i = new Intent(HalamanAdmin.this, HalamanSignin.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.steamlogo2)
                .setTitle("Admin Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessions.setLogin(false);
                        sessions.setSessid(0);
                        Intent i = new Intent(HalamanAdmin.this, HalamanSignin.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}