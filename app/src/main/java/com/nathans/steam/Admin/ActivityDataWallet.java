package com.nathans.steam.Admin;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nathans.steam.Adapter.AdapterWallet;
import com.nathans.steam.HalamanAdmin;
import com.nathans.steam.Model.ModelWallet;
import com.nathans.steam.R;
import com.nathans.steam.Server.BaseURL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ActivityDataWallet extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterWallet adapter;
    ListView list;

    ArrayList<ModelWallet> newsList = new ArrayList<ModelWallet>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_wallet);

        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterWallet(ActivityDataWallet.this, newsList);
        list.setAdapter(adapter);
        getAllWallet();
    }

    private void getAllWallet() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataWallet, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data wallet = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelWallet wallet = new ModelWallet();
                                    final String _id = jsonObject.getString("_id");
                                    final String walletType = jsonObject.getString("walletType");
                                    final String walletCode = jsonObject.getString("walletCode");
                                    final String walletPrc  = jsonObject.getString("walletPrc");
                                    final String pics  = jsonObject.getString("pics");
                                    wallet.setWalletCode(walletCode);
                                    wallet.setWalletType(walletType);
                                    wallet.setWalletPrc(walletPrc);
                                    wallet.setPics(pics);
                                    wallet.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(ActivityDataWallet.this, EDWallet.class);
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("walletCode", newsList.get(position).getWalletCode());
                                            a.putExtra("walletType", newsList.get(position).getWalletType());
                                            a.putExtra("walletPrc", newsList.get(position).getWalletPrc());
                                            a.putExtra("pics", newsList.get(position).getPics());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(wallet);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ActivityDataWallet.this, HalamanAdmin.class);
        startActivity(i);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}