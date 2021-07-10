package com.nathans.steam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.nathans.steam.User.Cart;
import com.nathans.steam.User.Wallet;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DanaWallet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dana_wallet);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnRickroll)
    void rickroll(View view){
        Intent rickroll = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        startActivity(rickroll);
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(DanaWallet.this, Wallet.class);
        startActivity(i);
        finish();
    }
}