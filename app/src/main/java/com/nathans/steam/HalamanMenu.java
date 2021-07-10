package com.nathans.steam;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.nathans.steam.User.Cart;
import com.nathans.steam.User.Games;
import com.nathans.steam.User.Wallet;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_menu);

        ButterKnife.bind(this);
    }

    //For Wallet
    @OnClick(R.id.btnViewWallet)
    void viewwallet(){
        Intent i = new Intent(HalamanMenu.this, Wallet.class);
        startActivity(i);
        finish();
    }

    //For Games
    @OnClick(R.id.btnViewGames)
    void viewgames(){
        Intent i = new Intent(HalamanMenu.this, Games.class);
        startActivity(i);
        finish();
    }

    //For Steam
    @OnClick(R.id.steamBrowse)
    void viewsteam(){
        Intent i = new Intent(HalamanMenu.this, Game10.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnUser)
    void viewuser(){
        Intent i = new Intent(HalamanMenu.this, HalamanUser.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnCart)
    void viewcart(){
        Intent i = new Intent(HalamanMenu.this, Cart.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnMenu)
    void viewmenu(){
        Intent i = new Intent(HalamanMenu.this, HalamanAlt.class);
        startActivity(i);
        finish();
    }
}