package org.devs.raghav.lifi;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private boolean flash;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send=(Button)findViewById(R.id.SendButtonID);
        Button receice=(Button)findViewById(R.id.ReceiveButtonID);
        Button test=(Button)findViewById(R.id.TestButtonID);

        hasFlash();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSend();
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    final CameraLatest cameraLatest = new CameraLatest();
                    count=0;
                    cameraLatest.init(initCamera2());
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if(count<=20) {
                                count++;
                                if (flash) {
                                    cameraLatest.flashOn();
                                    flash = false;
                                } else {
                                    cameraLatest.flashOff();
                                    flash = true;
                                }
                            }
                        }
                    },10,10);
                }
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

