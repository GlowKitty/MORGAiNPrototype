package morgain.morgainprototype;

import android.app.Application;
import android.content.Context;

/**
 * Created by GlowKitty on 2/16/2017.
 */

public class MyApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        if (context == null) {

        }
        return MyApplication.context;
    }
}
