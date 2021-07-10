package com.nathans.steam;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import com.nathans.steam.Session.PrefSettings;
import com.nathans.steam.Session.SessionManager;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanUser extends AppCompatActivity {

    TextView yourName;
    TextView yourEmail;

    SessionManager sessions;
    SharedPreferences prefs;
    PrefSettings prefSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_user);

        ButterKnife.bind(this);

        yourName = (TextView) findViewById(R.id.yourName);
        yourName.setText(PrefSettings.userName);
        yourEmail = (TextView) findViewById(R.id.yourEmail);
        yourEmail.setText(PrefSettings.email);

        prefSettings = new PrefSettings(this);
        prefs = prefSettings.getSharePreferences();

        sessions = new SessionManager(HalamanUser.this);

        prefSettings.isSignin(sessions, prefs);
    }

    @OnClick(R.id.btnExit)
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.steamlogo2)
                .setTitle("Log Out Steam.ind")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessions.setLogin(false);
                        sessions.setSessid(0);
                        Intent i = new Intent(HalamanUser.this, HalamanSignin.class);
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

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(HalamanUser.this, HalamanMenu.class);
        startActivity(i);
        finish();
    }
}