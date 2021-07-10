package com.nathans.steam.User;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.nathans.steam.HalamanMenu;
import com.nathans.steam.HalamanPy2;
import com.nathans.steam.R;
import com.nathans.steam.Server.BaseURL;
import com.squareup.picasso.Picasso;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailWallet extends AppCompatActivity {

    TextView txtWalletType, txtWalletPrc;
    ImageView imgWalletPics;

    String strWalletType, strWalletPrc, strImage, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_detail);

        ButterKnife.bind(this);

        txtWalletType = (TextView) findViewById(R.id.txtWalletType);
        txtWalletPrc  = (TextView) findViewById(R.id.txtWalletPrc);

        imgWalletPics = (ImageView) findViewById(R.id.Pics);

        Intent i = getIntent();
        strWalletType = i.getStringExtra("walletType");
        strWalletPrc  = i.getStringExtra("walletPrc");
        strImage      = i.getStringExtra("pics");
        _id           = i.getStringExtra("_id");

        txtWalletType.setText(strWalletType);
        txtWalletPrc.setText(strWalletPrc);
        Picasso.get().load(BaseURL.baseUrl + "pics/" + strImage)
                .into(imgWalletPics);
    }

    @OnClick(R.id.btnBuy)
    void buy(){
        Intent i = new Intent(DetailWallet.this, HalamanPy2.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnHome)
    void home(){
        Intent i = new Intent(DetailWallet.this, HalamanMenu.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(DetailWallet.this, Wallet.class);
        startActivity(i);
        finish();
    }

}
