package com.example.owner.savvybulb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudException;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.utils.Async;
import io.particle.android.sdk.utils.Toaster;

@SuppressWarnings("ALL")
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        findViewById(R.id.login_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        final String email = ((EditText) findViewById(R.id.email)).getText().toString();
                        final String password = ((EditText) findViewById(R.id.password)).getText().toString();

                        AsyncTask task = new AsyncTask() {
                            @Override
                            protected Object doInBackground(Object[] params) {
                                try {
                                    ParticleCloud.get(LoginActivity.this).logIn(email, password);

                                } catch (final ParticleCloudException e) {
                                    Runnable mainThread = new Runnable() {
                                        @Override
                                        public void run() {
                                            Toaster.l(LoginActivity.this, e.getBestMessage());
                                            e.printStackTrace();
                                            Log.d("info", e.getBestMessage());
                                            Log.d("info", e.getCause().toString());
                                        }
                                    };
                                    runOnUiThread(mainThread);

                                }
                                return null;
                            }

                        };

                        Async.executeAsync(ParticleCloud.get(v.getContext()), new Async.ApiWork<ParticleCloud, Object>() {

                            private ParticleDevice mDevice;

                            @Override
                            public Object callApi(ParticleCloud sparkCloud) throws ParticleCloudException, IOException {
                                sparkCloud.logIn(email, password);
                                sparkCloud.getDevices();
//                              mDevice = sparkCloud.getDevice("a9de5c7814eebaccda075036da7b8e1a482e0afb");
                                mDevice = sparkCloud.getDevice("3e003e000f47343432313031");

                                return -1;
                            }

                            @Override
                            public void onSuccess(Object value) {
                                Toaster.l(LoginActivity.this, "Logged in");
                                Particle.light = mDevice;
                                finish();
                            }

                            @Override
                            public void onFailure(ParticleCloudException e) {
                                Toaster.l(LoginActivity.this, e.getBestMessage());
                                e.printStackTrace();
                                Log.d("info", e.getBestMessage());
                            }
                        });


                    }
                }

        );
    }

}