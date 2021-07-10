package com.nathans.steam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanAlt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_alt);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAbout)
    void about(){
        Intent i = new Intent(HalamanAlt.this, HalamanAbout.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(HalamanAlt.this, HalamanMenu.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnSteam)
    void viewsteam(View view){
        Intent steam = new Intent(Intent.ACTION_VIEW, Uri.parse("https://store.steampowered.com"));
        startActivity(steam);
    }
}