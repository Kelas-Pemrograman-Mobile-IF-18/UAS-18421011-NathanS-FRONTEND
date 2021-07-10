package com.nathans.steam;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.nathans.steam.User.Cart;
import com.nathans.steam.User.DetailCart;
import com.nathans.steam.User.DetailWallet;
import com.nathans.steam.User.Games;
import com.nathans.steam.User.Wallet;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanPy2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment2);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnDana)
    void dana(){
        Intent i = new Intent(HalamanPy2.this, DanaWallet.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnAlfa)
    void alfa(){
        Intent i = new Intent(HalamanPy2.this, PayAlfaWallet.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnIndo)
    void indo(){
        Intent i = new Intent(HalamanPy2.this, PayIndoWallet.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(HalamanPy2.this, Wallet.class);
        startActivity(i);
        finish();
    }
}
