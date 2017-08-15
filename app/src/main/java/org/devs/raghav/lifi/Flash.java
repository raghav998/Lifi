package org.devs.raghav.lifi;

import android.os.Build;

public class Flash {
    private boolean isMarshmallow;
    private CameraLatest cameraLatest;
    private CameraLegacy cameraLegacy;
    public Flash()
    {
        isMarshmallow = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public void init()
    {
        if(isMarshmallow)
            cameraLatest.init();
        else
            cameraLegacy.init();
    }

    public void flashOn()
    {
        if(isMarshmallow)
            cameraLatest.flashOn();
        else
            cameraLegacy.flashOn();
    }
    public void flashOff()
    {
        if(isMarshmallow)
            cameraLatest.flashOff();
        else
            cameraLegacy.flashOff();
    }
}

