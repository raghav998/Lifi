package org.devs.raghav.lifi;

import android.content.pm.PackageManager;
import android.os.Build;
import android.content.Context;

public class Flash {
    private boolean isMarshmallow;
    private Object camera;
    public Flash()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            isMarshmallow = true;
        else
            isMarshmallow = false;
    }

    public void init()
    {
        if(!isMarshmallow)
            camera = new CameraLegacy();
        else

    }

}

