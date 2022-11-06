package com.mingrisoft.whj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.mingrisoft.whj.R;


import butterknife.ButterKnife;

public class MainActivity2 extends AppCompatActivity {


    boolean isTure=true,isFalse=true;

    CaptureManager captureManager;
    ImageButton imageButton;
    DecoratedBarcodeView barcodeView;
    boolean bTorch=false;

    private float dX;
    private float dY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);

        barcodeView=findViewById(R.id.dbv);
        imageButton=findViewById(R.id.but_light);
        barcodeView.setTorchListener(new DecoratedBarcodeView.TorchListener() {
            @SuppressLint("NewApi")
            @Override
            public void onTorchOn() {
                //imageButton.setBackground(getResources().getDrawable(R.drawable.flashlight_open));
                bTorch=true;
            }

            @SuppressLint("NewApi")
            @Override
            public void onTorchOff() {
                //imageButton.setBackground(getResources().getDrawable(R.drawable.flashlight_close));
                bTorch = false;
            }
        });

        //打开闪光灯
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bTorch){
                    barcodeView.setTorchOff();
                }else{
                    barcodeView.setTorchOn();
                }
            }
        });

        captureManager=new CaptureManager(this,barcodeView);
        captureManager.initializeFromIntent(getIntent(),savedInstanceState);
        captureManager.decode();


    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
        barcodeView.setTorchOff();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[],  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        captureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dX=event.getX();
        dY=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:
                float uX=event.getX();
                float uY= event.getY();
                if (uX-dX>50){
                    Toast.makeText(this, "向右滑动", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}