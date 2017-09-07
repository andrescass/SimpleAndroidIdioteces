package com.example.nosotros.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText textViewPhone;
    private EditText textViewWeb;
    private ImageButton buttonPhone;
    private ImageButton buttonWeb;
    private ImageButton buttonCam;

    private final int PHONE_CALL_CODE = 100;
    private final int CAM_CODE = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        textViewPhone = (EditText) findViewById(R.id.editTextPhone);
        textViewWeb = (EditText) findViewById(R.id.editTextWeb);
        buttonPhone = (ImageButton) findViewById(R.id.buttonPhone);
        buttonWeb = (ImageButton) findViewById(R.id.buttonWeb);
        buttonCam = (ImageButton) findViewById(R.id.buttonCam);

        // A activity anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = textViewPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    // Check Android Version
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OlderVersionCall(phoneNumber);
                    }
                }
            }

            private void OlderVersionCall(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if (CheckPermision(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Camera button

        buttonCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check Android Version
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CAM_CODE);
                } else {
                    OlderVersionCam();
                }
            }

            private void OlderVersionCam() {
                Intent intentCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
                if (CheckPermision(Manifest.permission.CAMERA)) {
                    startActivity(intentCam);
                } else {
                    Toast.makeText(ThirdActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Web browser button
        buttonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = textViewWeb.getText().toString();
                if(url != null && url.isEmpty()) {
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intentWeb);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //check requestCode
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                // Check granted
                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        // Permission granted
                        String phoneNumber = textViewPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentCall);
                    }
                }
                break;

            case CAM_CODE:
                Intent intentCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
                startActivity(intentCam);
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private boolean CheckPermision(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
