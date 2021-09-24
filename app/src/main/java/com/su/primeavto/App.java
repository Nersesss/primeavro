package com.su.primeavto;

import android.app.Application;

import com.su.primeavto.util.Connectivity;

public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Connectivity.init(app);
    }

    public static App getInstance() {
        return app;
    }

}
