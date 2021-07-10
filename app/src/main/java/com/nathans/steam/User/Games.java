package com.nathans.steam.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nathans.steam.Adapter.AdapterGames;
import com.nathans.steam.HalamanMenu;
import com.nathans.steam.Model.ModelGame;
import com.nathans.steam.R;
import com.nathans.steam.Server.BaseURL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Games extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterGames adapter;
    ListView list;

    ArrayList<ModelGame> newsList = new ArrayList<ModelGame>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        ButterKnife.bind(this);
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterGames(Games.this, newsList);
        list.setAdapter(adapter);
        getAllGames();
    }

    private void getAllGames() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataGame, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data game = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelGame game = new ModelGame();
                                    final String _id = jsonObject.getString("_id");
                                    final String namaGame = jsonObject.getString("namaGame");
                                    final String codeGame = jsonObject.getString("codeGame");
                                    final String developer = jsonObject.getString("developer");
                                    final String harga = jsonObject.getString("harga");
                                    final String rdate = jsonObject.getString("rdate");
                                    final String pics  = jsonObject.getString("pics");
                                    game.setCodeGame(codeGame);
                                    game.setNamaGame(namaGame);
                                    game.setDeveloper(developer);
                                    game.setHarga(harga);
                                    game.setRdate(rdate);
                                    game.setPics(pics);
                                    game.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(Games.this, DetailGames.class);
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("codeGame", newsList.get(position).getCodeGame());
                                            a.putExtra("namaGame", newsList.get(position).getNamaGame());
                                            a.putExtra("developer", newsList.get(position).getDeveloper());
                                            a.putExtra("harga", newsList.get(position).getHarga());
                                            a.putExtra("rdate", newsList.get(position).getRdate());
                                            a.putExtra("pics", newsList.get(position).getPics());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(game);
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

    @OnClick(R.id.btnSteam)
    void viewsteam(View view){
        Intent games = new Intent(Intent.ACTION_VIEW, Uri.parse("https://store.steampowered.com"));
        startActivity(games);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Games.this, HalamanMenu.class);
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
