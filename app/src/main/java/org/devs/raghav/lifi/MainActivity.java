package org.devs.raghav.lifi;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send=(Button)findViewById(R.id.SendButtonID);
        Button Receice=(Button)findViewById(R.id.ReceiveButtonID);

        hasFlash();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSend();
            }
        });
    }
    public void callSend()
    {
        Intent intent=new Intent(this,SendActivity.class);
        startActivity(intent);
    }
    public void hasFlash()
    {
        if(!getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            myToast("Your device has no Flash");
            finish();
        }
    }

    public void myToast(String s)
    {
        Toast.makeText(this.getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

    public Object initCamera2()
    {
        return getSystemService(CAMERA_SERVICE);
    }
}

