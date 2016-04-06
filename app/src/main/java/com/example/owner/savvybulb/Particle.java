
package com.example.owner.savvybulb;

import android.app.Activity;
import android.widget.Toast;

import java.io.IOException;
import io.particle.android.sdk.cloud.ParticleCloudException;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.utils.Async;
import static io.particle.android.sdk.utils.Py.list;


public class Particle extends Activity {

    static ParticleDevice light = null;

    static void sendDim(ParticleDevice light, int dim) {

        final String command = String.valueOf(dim);
        if (light != null) {
            try {
                Async.executeAsync(light, new Async.ApiWork<ParticleDevice, Integer>() {
                    public Integer callApi(ParticleDevice particleDevice)
                            throws ParticleCloudException, IOException {
                        try {
                            return particleDevice.callFunction("setDimming", list(command));
                        } catch (ParticleDevice.FunctionDoesNotExistException e) {
                            e.printStackTrace();
                        }
                        return -1;
                    }

                    @Override
                    public void onSuccess(Integer value) {}

                    @Override
                    public void onFailure(ParticleCloudException e) {}
                });
            } catch (Exception e) { /*strange error*/ }
        }
    }

    static void sendAlarm(ParticleDevice light, int unixTime, int pcBright) {
        // UNIX time is number of seconds since Jan 1, 1970, pcBright is % brightness when alarm goes off.

        final String command = String.valueOf(unixTime) + "-" + String.valueOf(pcBright);
        System.out.println(unixTime); // <- prints for testing

        if (light != null) {
            Async.executeAsync(light, new Async.ApiWork<ParticleDevice, Integer>() {
                public Integer callApi(ParticleDevice particleDevice)
                        throws ParticleCloudException, IOException {
                    try {
                        return particleDevice.callFunction("setAlarm", list(command));
                    } catch (ParticleDevice.FunctionDoesNotExistException e) {
                        e.printStackTrace();
                    }
                    return -1;
                }

                @Override
                public void onSuccess(Integer value) {
                    System.out.println("Alarm Success");
                }

                @Override
                public void onFailure(ParticleCloudException e) {
                    System.out.println("Alarm Failure");
                }
            });
        } else {/* Toast doesn't have a context here !! */}
    }

    static void sendTimer(ParticleDevice light, int time) {
        // time is an integer containing number of seconds until timer goes off.

        if (light != null) {

            final String command = String.valueOf(time);
            System.out.println(command);

            if (light != null) {
                Async.executeAsync(light, new Async.ApiWork<ParticleDevice, Integer>() {
                    public Integer callApi(ParticleDevice particleDevice)
                            throws ParticleCloudException, IOException {
                        try {
                            return particleDevice.callFunction("setTimer", list(command));
                        } catch (ParticleDevice.FunctionDoesNotExistException e) {
                            e.printStackTrace();
                        }
                        return -1;
                    }

                    @Override
                    public void onSuccess(Integer value) {
                        System.out.println("Timer Success");
                    }

                    @Override
                    public void onFailure(ParticleCloudException e) {
                        System.out.println("Timer Failure");
                    }
                });
            }
        }
    }
}
