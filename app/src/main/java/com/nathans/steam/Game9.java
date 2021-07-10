package com.nathans.steam;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Game9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game9);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnStoreGame9)
    void viewgame9(View view){
        Intent game9 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://store.steampowered.com/app/1015500/WWE_2K20/"));
        startActivity(game9);
    }

    @OnClick(R.id.btnHome)
    void home(){
        Intent i = new Intent(Game9.this, HalamanMenu.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(Game9.this, HalamanGames.class);
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