package org.devs.raghav.lifi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class SendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Button back = (Button) findViewById(R.id.BackButton);
        Button send = (Button) findViewById(R.id.SendButtonID);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    try {
                        CameraLatest cameraLatest = new CameraLatest();
                        cameraLatest.init(getSystemService(CAMERA_SERVICE));
                        EditText text = (EditText) findViewById(R.id.Text);
                        sendData(cameraLatest, String.valueOf(text.getText()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void sendData(CameraLatest camera, String s) {
        TextView text = (TextView) findViewById(R.id.Empty);
        TextView bin = (TextView) findViewById(R.id.textView5);
        byte[] ascii = s.getBytes();
        for (byte no : ascii) {
            text.setText(text.getText().toString() + no + " ");
            for (int i = 0; i < 8; i++) {
                bin.setText(bin.getText().toString() + (no % 2));
                no /= 2;
            }
        }
        try {
            String str=bin.getText().toString();
            for(int i=0;i<str.length();i++) {
                int temp=Integer.parseInt(str.substring(i,i+1));
                if(temp==1)
                    camera.flashOn();
                else
                    camera.flashOff();
//                TimeUnit.SECONDS.sleep(1);
                TimeUnit.MICROSECONDS.sleep(100);
            }
            camera.flashOff();
            Toast.makeText(this, "Text Transmitted", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void callback() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
