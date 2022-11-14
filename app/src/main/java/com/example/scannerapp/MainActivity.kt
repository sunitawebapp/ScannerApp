package com.example.scannerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.scannerapp.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        // we need to create the object
        // of IntentIntegrator class
        // which is the class of QR library
        val intentIntegrator = IntentIntegrator(this)
        intentIntegrator.setPrompt("Scan a barcode or QR Code")

        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCameraId(0);  // Use a specific camera of the device

        intentIntegrator.initiateScan()
        // Replace with your own java class location here
        // Replace with your own java class location here
        intentIntegrator.setCaptureActivity(CaptureActivity::class.java)
        intentIntegrator.setBeepEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.contents == null) {
                Toast.makeText(baseContext, "Cancelled", Toast.LENGTH_SHORT).show()
            } else {
                // if the intentResult is not null we'll set
                // the content and format of scan message
                binding. tvQR.setText(intentResult.contents)

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}