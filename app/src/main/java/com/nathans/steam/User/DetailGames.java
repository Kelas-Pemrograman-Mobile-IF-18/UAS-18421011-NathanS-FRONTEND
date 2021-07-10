package com.nathans.steam.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nathans.steam.Admin.ActivityDataGames;
import com.nathans.steam.Admin.EDGame;
import com.nathans.steam.Admin.InputGame;
import com.nathans.steam.HalamanMenu;
import com.nathans.steam.R;
import com.nathans.steam.Server.BaseURL;
import com.nathans.steam.Server.VolleyMultipart;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailGames extends AppCompatActivity {

    TextView txtNamaGame, txtDeveloper, txtHarga, txtRdate;
    ImageView imgGamePics;

    Button btnCart;

    Bitmap bitmap;

    String strNamaGame, strDeveloper, strHarga, strRdate, strImage, _id;

    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_detail);

        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        txtNamaGame  = (TextView) findViewById(R.id.txtNamaGame);
        txtDeveloper = (TextView) findViewById(R.id.txtDeveloper);
        txtHarga     = (TextView) findViewById(R.id.txtHarga);
        txtRdate     = (TextView) findViewById(R.id.txtRdate);

        imgGamePics = (ImageView) findViewById(R.id.Pics);

        Intent i = getIntent();
        strNamaGame  = i.getStringExtra("namaGame");
        strDeveloper = i.getStringExtra("developer");
        strHarga     = i.getStringExtra("harga");
        strRdate     = i.getStringExtra("rdate");
        strImage     = i.getStringExtra("pics");
        _id          = i.getStringExtra("_id");

        btnCart = (Button) findViewById(R.id.btnCart);

        txtNamaGame.setText(strNamaGame);
        txtDeveloper.setText(strDeveloper);
        txtHarga.setText("Rp. " + strHarga);
        txtRdate.setText(strRdate);
        Picasso.get().load(BaseURL.baseUrl + "pics/" + strImage)
                .into(imgGamePics);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCart(bitmap);
            }
        });
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null){
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private void addCart(final Bitmap bitmap) {
        pDialog.setMessage("Wait...");
        showDialog();
        VolleyMultipart volleyMultipartRequest = new VolleyMultipart(Request.Method.POST, BaseURL.inputCart,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        mRequestQueue.getCache().clear();
                        hideDialog();
                        try {
                            JSONObject jsonObject = new JSONObject(new String(response.data));
                            System.out.println("ress = " + jsonObject.toString());
                            String strMsg = jsonObject.getString("msg");
                            boolean status= jsonObject.getBoolean("error");
                            if(status == false){
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                Intent i = new Intent(DetailGames.this, Cart.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();
                        Toast.makeText(DetailGames.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("namaGame", txtNamaGame.getText().toString());
                params.put("harga", txtHarga.getText().toString());
                params.put("pics", strImage.getBytes().toString());
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pics", new VolleyMultipart.DataPart(imagename + ".jpg", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue = Volley.newRequestQueue(DetailGames.this);
        mRequestQueue.add(volleyMultipartRequest);
    }

    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }

    @OnClick(R.id.btnHome)
    void home(){
        Intent i = new Intent(DetailGames.this, HalamanMenu.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(DetailGames.this, Games.class);
        startActivity(i);
        finish();
    }

}
