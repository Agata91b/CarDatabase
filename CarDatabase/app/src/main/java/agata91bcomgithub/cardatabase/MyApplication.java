package agata91bcomgithub.cardatabase;

import android.app.Application;
import android.graphics.Bitmap;

import com.facebook.stetho.Stetho;


/**
 * Created by RENT on 2017-03-25.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        boolean isInDebug = BuildConfig.DEBUG;
        Stetho.initializeWithDefaults(this);
    }
}
