package com.nathans.steam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanWallet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_wallet);

        ButterKnife.bind(this);
        Intent i = getIntent();
    }

    @OnClick(R.id.btnWallet1)
    void viewwallet1(){
        Intent i = new Intent(HalamanWallet.this, Wallet1.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnWallet2)
    void viewwallet2(){
        Intent i = new Intent(HalamanWallet.this, Wallet2.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnWallet3)
    void viewwallet3(){
        Intent i = new Intent(HalamanWallet.this, Wallet3.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnWallet4)
    void viewwallet4(){
        Intent i = new Intent(HalamanWallet.this, Wallet4.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnWallet5)
    void viewwallet5(){
        Intent i = new Intent(HalamanWallet.this, Wallet5.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnWallet6)
    void viewwallet6(){
        Intent i = new Intent(HalamanWallet.this, Wallet6.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnWallet7)
    void viewwallet7(){
        Intent i = new Intent(HalamanWallet.this, Wallet7.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnWallet8)
    void viewwallet8(){
        Intent i = new Intent(HalamanWallet.this, Wallet8.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnHome)
    void home(){
        Intent i = new Intent(HalamanWallet.this, HalamanMenu.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(HalamanWallet.this, HalamanMenu.class);
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