package org.devs.raghav.lifi;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class CameraLegacy {
    private Camera camera;
    private Parameters parameters;
    private boolean isFlashOn;
    public void init()
    {
        if(camera==null)
        {
            try
            {
                camera=Camera.open();
                parameters=camera.getParameters();
                isFlashOn=false;
            }
            catch (RuntimeException e)
            {
                new MainActivity().myToast("Error while opening camera using camera legacy class");
            }
        }
    }
    public void flashOn()
    {
        if((camera==null)||(parameters==null)||(isFlashOn))
            return;
        parameters=camera.getParameters();
        parameters.setFlashMode(Parameters.FLASH_MODE_ON);
        camera.setParameters(parameters);
        camera.startPreview();
        isFlashOn=true;
    }

    public void flashOff()
    {
        if((camera==null)||(parameters==null)||(!isFlashOn))
            return;
        parameters=camera.getParameters();
        parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
        camera.stopPreview();
        isFlashOn=false;
    }
}
