package com.nathans.steam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nathans.steam.Server.BaseURL;
import com.nathans.steam.Session.PrefSettings;
import com.nathans.steam.Session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanSignin extends AppCompatActivity {

    EditText username, password;
    ProgressDialog progressDialog;
    private RequestQueue mRequestQueue;

    SessionManager sessions;
    SharedPreferences prefs;
    PrefSettings prefSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_signin);

        ButterKnife.bind(this);
        mRequestQueue = Volley.newRequestQueue(this);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        prefSettings = new PrefSettings(this);
        prefs = prefSettings.getSharePreferences();

        sessions = new SessionManager(this);
        prefSettings.CheckLogin(sessions, prefs);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    @OnClick(R.id.btnSignin)
    void signin(){
        String strusername = username.getText().toString();
        String strpassword = password.getText().toString();

        if (strusername.isEmpty()){
            Toast.makeText(getApplicationContext(), "Username Cannot Be Empty", Toast.LENGTH_LONG).show();
        } else if (strpassword.isEmpty()){
            Toast.makeText(getApplicationContext(), "Password Cannot Be Empty", Toast.LENGTH_LONG).show();
        } else {
            signin(strusername, strpassword);
        }
    }

    @OnClick(R.id.btnJoin)
    void join(){
        Intent i = new Intent(HalamanSignin.this, HalamanSignup.class);
        startActivity(i);
        finish();
    }

    public void signin(String userName, String password){
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userName", userName);
        params.put("password", password);

        progressDialog.setMessage("Please wait...");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(BaseURL.signin, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status= response.getBoolean("error");

                            if(status == false){
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                String data = response.getString("data");
                                JSONObject jsonObject = new JSONObject(data);
                                String role = jsonObject.getString("role");
                                String _id  = jsonObject.getString("_id");
                                String userName  = jsonObject.getString("userName");
                                String email  = jsonObject.getString("email");
                                String password  = jsonObject.getString("password");
                                sessions.setLogin(true);
                                prefSettings.storeRegIdSharedPreferences(HalamanSignin.this, _id, userName, email, password, role, prefs);

                                //Role Options
                                if (role.equals("1")) {
                                    Intent i = new Intent(HalamanSignin.this, HalamanAdmin.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Intent i = new Intent(HalamanSignin.this, HalamanMenu.class);
                                    startActivity(i);
                                    finish();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });
        mRequestQueue.add(req);
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

    private void showDialog(){
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    private void hideDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
