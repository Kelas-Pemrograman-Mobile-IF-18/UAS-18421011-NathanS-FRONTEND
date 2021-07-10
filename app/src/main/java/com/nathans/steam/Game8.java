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

public class Game8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game8);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnStoreGame8)
    void viewgame8(View view){
        Intent game8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://store.steampowered.com/app/892970/Valheim/"));
        startActivity(game8);
    }

    @OnClick(R.id.btnHome)
    void home(){
        Intent i = new Intent(Game8.this, HalamanMenu.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(Game8.this, HalamanGames.class);
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