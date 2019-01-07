package com.example.jaroslav.taskfromforasoft;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
