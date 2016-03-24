
package com.example.owner.savvybulb;

import android.util.Log;
import junit.framework.Assert;
import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleCloudException;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.utils.Async;
import io.particle.android.sdk.utils.Toaster;


public class Particle {

    static void login()
    {
        try {
            ParticleCloudSDK.getCloud().logIn("alanross17@me.com", "getlitwirelessly");
        } catch (ParticleCloudException e) {e.printStackTrace();}
    }

    static ParticleDevice light() {
        ParticleDevice light = null;
        try {
            light = ParticleCloudSDK.getCloud().getDevice("a9de5c7814eebaccda075036da7b8e1a482e0afb");
        } catch (ParticleCloudException e) { e.printStackTrace(); }
    return light;
    }

    static void sendDim(ParticleDevice light, int dim)
    {    }


}



