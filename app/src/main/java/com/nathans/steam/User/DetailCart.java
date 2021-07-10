package com.nathans.steam.User;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.nathans.steam.PayAlfaGames;
import com.nathans.steam.DanaGames;
import com.nathans.steam.PayIndoGames;
import com.nathans.steam.R;
import com.nathans.steam.Server.BaseURL;

import org.json.JSONException;
import java.lang.reflect.Method;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailCart extends AppCompatActivity {

    TextView txtNamaGame, txtHarga;
    Button btnDelete;
    String strNamaGame, strHarga, _id;

    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detail);

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
        txtHarga     = (TextView) findViewById(R.id.txtHarga);
        btnDelete    = (Button) findViewById(R.id.btnDelete);

        Intent i = getIntent();
        strNamaGame  = i.getStringExtra("namaGame");
        strHarga     = i.getStringExtra("harga");
        _id          = i.getStringExtra("_id");

        txtNamaGame.setText(strNamaGame);
        txtHarga.setText(strHarga);

        btnDelete.setOnClickListener(v -> {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailCart.this);

        builder.setTitle("Confirm");
        builder.setMessage("Delete " + strNamaGame + " ? ");

        builder.setPositiveButton("Delete", (dialog, which) -> {
            // Do nothing but close the dialog
            deleteData();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {

            // Do nothing
            dialog.dismiss();
        });

        AlertDialog alert = builder.create();
        alert.show();

    });
}

    public void addPermission() {
        Dexter.withActivity(DetailCart.this)
                .withPermissions(

                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //Toast.makeText(getActivity(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(error -> Toast.makeText(DetailCart.this, "Some Error! ", Toast.LENGTH_SHORT).show())
                .onSameThread()
                .check();
    }

    public void deleteData(){

        pDialog.setMessage("Wait...");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.DELETE,BaseURL.deleteCart + _id, null,
                response -> {
                    hideDialog();
                    try {
                        String strMsg = response.getString("msg");
                        boolean status= response.getBoolean("error");
                        if(status == false){
                            Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(DetailCart.this, Cart.class);
                            startActivity(i);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            VolleyLog.e("Error: ", error.getMessage());
            hideDialog();
        });
        mRequestQueue.add(req);
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

    @OnClick(R.id.btnDana)
    void dana(){
        Intent i = new Intent(DetailCart.this, DanaGames.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnAlfa)
    void alfa(){
        Intent i = new Intent(DetailCart.this, PayAlfaGames.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnIndo)
    void indo(){
        Intent i = new Intent(DetailCart.this, PayIndoGames.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnBack)
    void back(){
        Intent i = new Intent(DetailCart.this, Cart.class);
        startActivity(i);
        finish();
    }

}
