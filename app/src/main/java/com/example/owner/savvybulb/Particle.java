
package com.example.owner.savvybulb;

import android.app.Activity;
import android.util.Log;
import junit.framework.Assert;
import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleCloudException;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.utils.Async;
import io.particle.android.sdk.utils.Toaster;

import static io.particle.android.sdk.utils.Py.list;


public class Particle extends Activity {

    static ParticleDevice light = null;

    static void login() {
        final String email = "alanross17@me.com";
        final String password = "getlitwirelessly";

        try {
            ParticleCloudSDK.getCloud().logIn(email, password);
        } catch (ParticleCloudException e) {
            e.printStackTrace();
        }
    }

    static ParticleDevice light() {
        ParticleDevice light = null;
        try {
            light = ParticleCloudSDK.getCloud().getDevice("a9de5c7814eebaccda075036da7b8e1a482e0afb");
        } catch (ParticleCloudException e) {
            e.printStackTrace();
        }
        return light;
    }

    static void sendDim(ParticleDevice light, int dim) {
        int resultCode = 0;
        String dimming = String.valueOf(dim);

        try {
            resultCode = light.callFunction("setDimming", list("dimming", dimming));
        } catch (ParticleCloudException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParticleDevice.FunctionDoesNotExistException e) {
            e.printStackTrace();
        }

//        Toaster.s(this, "Result of calling setDimming: " + resultCode);
    }

    static void sendAlarm(ParticleDevice light, int stdTime, int pcBright)
    {
        // stdTime has to be converted into UNIX time, pcBright is % brightness when alarm goes off.

    }

    static void sendTimer(ParticleDevice light, int time)
    {
        // time is an integer containing number of seconds UNTIL timer goes off.

    }

}
