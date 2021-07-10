package com.nathans.steam.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
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
import com.nathans.steam.R;
import com.nathans.steam.Server.BaseURL;
import com.nathans.steam.Server.VolleyMultipart;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EDGame extends AppCompatActivity {

    String codeGame, namaGame, developer, rdate, harga, strPics, _id;
    EditText edtCodeGame, edtNamaGame, edtDeveloper, edtRdate, edtHarga;
    ImageView gamePics;
    Button btnTakeImg, btnUploadGambar, delGame;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private RequestQueue mRequestQueue;

    Bitmap bitmap;

    private final int CameraR_PP = 1;
    String mCurrentPhotoPath;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edgame);

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

        edtCodeGame   = (EditText) findViewById(R.id.edtCodeGame);
        edtNamaGame   = (EditText) findViewById(R.id.edtNamaGame);
        edtDeveloper  = (EditText) findViewById(R.id.edtDeveloper);
        edtRdate      = (EditText) findViewById(R.id.edtRdate);
        edtHarga      = (EditText) findViewById(R.id.edtHarga);

        gamePics      = (ImageView) findViewById(R.id.gambar);

        btnTakeImg      = (Button) findViewById(R.id.btnTakeImage);
        btnUploadGambar = (Button) findViewById(R.id.btnUploadGambar);
        delGame         = (Button) findViewById(R.id.delGame);

        Intent i = getIntent();
        codeGame   = i.getStringExtra("codeGame");
        namaGame   = i.getStringExtra("namaGame");
        developer  = i.getStringExtra("developer");
        rdate      = i.getStringExtra("rdate");
        harga      = i.getStringExtra("harga");
        strPics    = i.getStringExtra("pics");
        _id = i.getStringExtra("_id");

        edtCodeGame.setText(codeGame);
        edtNamaGame.setText(namaGame);
        edtDeveloper.setText(developer);
        edtRdate.setText(rdate);
        edtHarga.setText(harga);
        Picasso.get().load(BaseURL.baseUrl + "pics/" + strPics)
                .into(gamePics);

//        btnTakeImg.setOnClickListener(v -> takeImage());

        btnUploadGambar.setOnClickListener(v -> editDataDenganGambar(bitmap));

        btnTakeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check Runtime Permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        //permission denied
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show pop up runtime
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else {
                        //permission granted
                        pickImageFromGallery();
                    }
                }
                else {
                    //system os less than marshmallow
                    pickImageFromGallery();
                }
            }
        });

        delGame.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(EDGame.this);

            builder.setTitle("Confirm");
            builder.setMessage("Delete " + codeGame + " ? ");

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

//    public void takeImage(){
//        addPermission();
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (cameraIntent.resolveActivity(EDGame.this.getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                Log.i("Tags", "IOException");
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//                startActivityForResult(cameraIntent, CameraR_PP);
//            }
//        }
//    }


//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  // prefix
//                ".jpg",         // suffix
//                storageDir      // directory
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        return image;
//    }

//    public void addPermission() {
//        Dexter.withActivity(EDGame.this)
//                .withPermissions(
//
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        // check if all permissions are granted
//                        if (report.areAllPermissionsGranted()) {
//                            //Toast.makeText(getActivity(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
//                        }
//
//                        // check for permanent denial of any permission
//                        if (report.isAnyPermissionPermanentlyDenied()) {
//                            // show alert dialog navigating to Settings
//
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                }).
//                withErrorListener(error -> Toast.makeText(EDGame.this, "Some Error! ", Toast.LENGTH_SHORT).show())
//                .onSameThread()
//                .check();
//    }

    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    pickImageFromGallery();
                }
                else {
                    //permission was denies
                    Toast.makeText(this, "Permission Denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //handle result of picked image
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //set image to image
            gamePics.setImageURI(data.getData());
            btnUploadGambar.setVisibility(View.VISIBLE);
        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null){
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private void editDataDenganGambar(final Bitmap bitmap) {
        pDialog.setMessage("Uploading Picture!");
        showDialog();
        VolleyMultipart volleyMultipartRequest = new VolleyMultipart(Request.Method.PUT, BaseURL.updateGame + _id,
                response -> {
                    mRequestQueue.getCache().clear();
                    hideDialog();
                    try {
                        JSONObject jsonObject = new JSONObject(new String(response.data));
                        System.out.println("ress = " + jsonObject.toString());
                        String strMsg = jsonObject.getString("msg");
                        boolean status= jsonObject.getBoolean("error");
                        if(status == false){
                            Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(EDGame.this, ActivityDataGames.class);
                            startActivity(i);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    hideDialog();
                    Toast.makeText(EDGame.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("codeGame", edtCodeGame.getText().toString());
                params.put("namaGame", edtNamaGame.getText().toString());
                params.put("developer", edtDeveloper.getText().toString());
                params.put("rdate", edtRdate.getText().toString());
                params.put("harga", edtHarga.getText().toString());
                return params;
            }
            @Override
            protected Map<String, VolleyMultipart.DataPart> getByteData() {
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
        mRequestQueue = Volley.newRequestQueue(EDGame.this);
        mRequestQueue.add(volleyMultipartRequest);
    }

    @OnClick(R.id.upGame)
    void updateGame(){
        // Post params to be sent to the server
        Map<String, String> params = new HashMap<>();
        params.put("codeGame", edtCodeGame.getText().toString());
        params.put("namaGame", edtNamaGame.getText().toString());
        params.put("developer", edtDeveloper.getText().toString());
        params.put("rdate", edtRdate.getText().toString());
        params.put("harga", edtHarga.getText().toString());
        params.put("pics", strPics);

        pDialog.setMessage("Wait...");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.PUT,BaseURL.updateGame + _id, new JSONObject(params),
                response -> {
                    hideDialog();
                    try {
                        String strMsg = response.getString("msg");
                        boolean status= response.getBoolean("error");
                        if(status == false){
                            Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(EDGame.this, ActivityDataGames.class);
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

    public void deleteData(){

        pDialog.setMessage("Wait...");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.DELETE,BaseURL.deleteGame + _id, null,
                response -> {
                    hideDialog();
                    try {
                        String strMsg = response.getString("msg");
                        boolean status= response.getBoolean("error");
                        if(status == false){
                            Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(EDGame.this, ActivityDataGames.class);
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(EDGame.this, ActivityDataGames.class);
        startActivity(i);
        finish();
    }
}
