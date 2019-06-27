package com.smithereens.nagarikbadapatra.qr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;
import com.smithereens.nagarikbadapatra.MainActivity;
import com.smithereens.nagarikbadapatra.R;
import com.smithereens.nagarikbadapatra.fragments.InformationFragment;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_qrcode);
        mScannerView = new ZXingScannerView(this);
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.qr_code_container);// Programmatically initialize the scanner view
        contentFrame.addView(mScannerView);                // Set the scanner view as the content view

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    protected void onStart() {
        super.onStart();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        String qrText = rawResult.getText();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("loadfragment", "information");
        intent.putExtra("officeid", qrText);
        startActivity(intent);
    }
}

