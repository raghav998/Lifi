package org.devs.raghav.lifi;

import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraAccessException;

public class CameraLatest {
    private CameraManager camera;
    private String cameraID;
    private boolean isFlashOn;
    public void init()
    {
        camera=(CameraManager)new MainActivity().initCamera2();
        try
        {
                cameraID=camera.getCameraIdList()[0];
        }
        catch (CameraAccessException e)
        {
            new MainActivity().myToast("Error while accesing camera in camera2");
        }
        isFlashOn=false;
    }
    public void flashOn()
    {
        try
        {
            if(!isFlashOn)
                camera.setTorchMode(cameraID,true);
            isFlashOn=true;
        }
        catch (CameraAccessException e)
        {
            new MainActivity().myToast("Error while turning on flash");
        }
    }
    public void flashOff()
    {
        try
        {
            if(isFlashOn)
                camera.setTorchMode(cameraID,false);
            isFlashOn=false;
        } catch (CameraAccessException e) {
            new MainActivity().myToast("Error whiel turning off flash");
        }
    }
}
