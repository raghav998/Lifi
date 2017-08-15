package org.devs.raghav.lifi;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

class CameraLegacy {
    private Camera camera;
    private Parameters parameters;
    private boolean isFlashOn;
    void init()
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
                Log.e("error while opening",e.getMessage());
            }
        }
    }
    void flashOn()
    {
        if((camera==null)||(parameters==null)||(isFlashOn))
            return;
        parameters=camera.getParameters();
        parameters.setFlashMode(Parameters.FLASH_MODE_ON);
        camera.setParameters(parameters);
        camera.startPreview();
        isFlashOn=true;
    }

    void flashOff()
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
