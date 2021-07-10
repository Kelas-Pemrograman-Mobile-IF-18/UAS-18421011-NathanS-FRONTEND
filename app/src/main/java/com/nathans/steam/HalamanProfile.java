package com.nathans.steam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.nathans.steam.Session.PrefSettings;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanProfile extends AppCompatActivity {

    TextView myName;
    TextView myEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_profile);

        ButterKnife.bind(this);

        myName = (TextView) findViewById(R.id.myName);
        myName.setText(PrefSettings.userName);
        myEmail = (TextView) findViewById(R.id.myEmail);
        myEmail.setText(PrefSettings.email);
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(HalamanProfile.this, HalamanAdmin.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.steamlogo2)
                .setTitle("Exit Steam.ind")
                .setMessage("Are you sure?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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