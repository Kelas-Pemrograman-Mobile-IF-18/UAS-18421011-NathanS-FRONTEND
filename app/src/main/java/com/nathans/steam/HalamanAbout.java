package com.nathans.steam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_about);

        ButterKnife.bind(this);
    }

    public void cellphone(View view) {
        Uri uri = Uri.parse("tel:08986735932");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    public void email(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "nnexus016@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Technical Support");
        intent.putExtra(Intent.EXTRA_TEXT, "Hello Nathan, ");

        try {
            startActivity(Intent.createChooser(intent, "Choose an Email :"));
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(HalamanAbout.this, HalamanAlt.class);
        startActivity(i);
        finish();
    }
}