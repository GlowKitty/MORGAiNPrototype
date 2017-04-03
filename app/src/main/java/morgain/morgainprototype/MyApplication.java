package morgain.morgainprototype;

import android.app.Application;
import android.content.Context;

/**
 * Created by GlowKitty on 2/16/2017.
 */

public class MyApplication extends Application {
    /*private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }*/

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        if (context == null) {
            //todo or something
        }
        return MyApplication.context;
    }
}