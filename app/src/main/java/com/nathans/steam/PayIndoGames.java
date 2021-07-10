package com.nathans.steam;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayIndoGames extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_indogames);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnClose)
    void close(){
        Intent i = new Intent(PayIndoGames.this, HalamanMenu.class);
        startActivity(i);
        finish();
    }

}
