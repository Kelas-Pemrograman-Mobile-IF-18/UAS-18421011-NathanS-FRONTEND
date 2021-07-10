package com.nathans.steam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanGames extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_games);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnGame1)
    void viewgame1(){
        Intent i = new Intent(HalamanGames.this, Game1.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnGame2)
    void viewgame2(){
        Intent i = new Intent(HalamanGames.this, Game2.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnGame3)
    void viewgame3(){
        Intent i = new Intent(HalamanGames.this, Game3.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnGame4)
    void viewgame4(){
        Intent i = new Intent(HalamanGames.this, Game4.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnGame5)
    void viewgame5(){
        Intent i = new Intent(HalamanGames.this, Game5.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnGame6)
    void viewgame6(){
        Intent i = new Intent(HalamanGames.this, Game6.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnGame7)
    void viewgame7(){
        Intent i = new Intent(HalamanGames.this, Game7.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnGame8)
    void viewgame8(){
        Intent i = new Intent(HalamanGames.this, Game8.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnGame9)
    void viewgame9(){
        Intent i = new Intent(HalamanGames.this, Game9.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnHome)
    void home(){
        Intent i = new Intent(HalamanGames.this, HalamanMenu.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(HalamanGames.this, HalamanMenu.class);
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